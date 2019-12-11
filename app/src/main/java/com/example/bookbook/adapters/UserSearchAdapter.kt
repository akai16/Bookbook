package com.example.bookbook.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbook.R
import com.example.bookbook.entities.User
import kotlinx.android.synthetic.main.recycler_user_search_cell.view.*

class UserSearchAdapter(private val context: Context, val userList: List<User>) : RecyclerView.Adapter<UserSearchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_user_search_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.userName.text = user.name
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.txt_user_name
    }

}