package com.example.bookbook.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_book_search_cell.view.*
import java.lang.Exception

class BookSearchAdapter(private val context: Context, private val bookList: List<Book>):
    RecyclerView.Adapter<BookSearchAdapter.ViewHolder>() {

    val user = FirebaseAuth.getInstance().currentUser
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_book_search_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val book = bookList[position]

        // Set Book Title
        holder.title.text = book.title

        // Set Book Cover Image
        Picasso.get().load(book.image).into(holder.image, object: Callback {
            override fun onSuccess() {
                Log.d(Consts.DEBUG_TAG, "Imagem baixada")
            }

            override fun onError(e: Exception?) {
                e!!.printStackTrace()
                Log.d(Consts.DEBUG_TAG, "Erro ao baixar imagem")
            }

        })

        // Add Btn Listeners
        holder.wishListBtn.setOnClickListener {
            db.collection(FirebaseConsts.USERS_COLLECTION).document(user!!.uid).update(FirebaseConsts.USER.WISH_LIST, FieldValue.arrayUnion(book.id))
            Toast.makeText(context, "Livro adicionado a Lista de Desejos", Toast.LENGTH_SHORT).show()
        }

        holder.favBooksBtn.setOnClickListener {
            db.collection(FirebaseConsts.USERS_COLLECTION).document(user!!.uid).update(FirebaseConsts.USER.FAV_BOOKS_LIST, FieldValue.arrayUnion(book.id))
            Toast.makeText(context, "Livro adicionado aos Favoritos", Toast.LENGTH_SHORT).show()
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.txt_book_title
        val image: ImageView = itemView.img_book_cover
        val wishListBtn: Button = itemView.btn_add_book_wish_list
        val favBooksBtn: Button = itemView.btn_add_book_fav_list
    }
}