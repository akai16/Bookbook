package com.example.bookbook.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_book_search_cell.view.*
import java.lang.Exception

class BookSearchAdapter(private val context: Context, private val bookList: List<Book>):
    RecyclerView.Adapter<BookSearchAdapter.ViewHolder>() {

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

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.txt_book_title
        val image: ImageView = itemView.img_book_cover
    }
}