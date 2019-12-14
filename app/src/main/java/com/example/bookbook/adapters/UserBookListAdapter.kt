package com.example.bookbook.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import com.example.bookbook.views.activities.BookDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_book_list_cell.view.*
import kotlinx.android.synthetic.main.recycler_book_search_cell.view.*

class UserBookListAdapter( private val ctx: Context, private val list: List<Book>): RecyclerView.Adapter<UserBookListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.recycler_book_search_cell, parent, false)

        view.btn_add_book_fav_list.visibility = View.INVISIBLE
        view.btn_add_book_wish_list.visibility = View.INVISIBLE

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = list[position]

        holder.title?.text  = book.title
        Picasso.get().load(book.image).into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, BookDetailsActivity::class.java)
            intent.putExtra(Consts.EXTRA_BOOK_DATA, book)
            ctx.startActivity(intent)
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView? = view.txt_book_title
        val image: ImageView? = view.img_book_cover
    }
}