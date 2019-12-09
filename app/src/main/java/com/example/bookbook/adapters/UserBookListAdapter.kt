package com.example.bookbook.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import com.example.bookbook.views.activities.BookDetailsActivity
import kotlinx.android.synthetic.main.recycler_fav_book_cell.view.*

class UserBookListAdapter(private val list: List<Book>, private val ctx: Context): RecyclerView.Adapter<UserBookListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.recycler_fav_book_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = list[position]

        holder.title?.text  = book.title
        holder.author?.text = book.author

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, BookDetailsActivity::class.java)
            intent.putExtra(Consts.EXTRA_BOOK_DATA, book)
            ctx.startActivity(intent)
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title:  TextView? = view.txt_fav_book_title
        var author: TextView? = view.txt_fav_book_authors
    }
}