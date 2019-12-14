package com.example.bookbook.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.User
import com.example.bookbook.views.activities.ProfileActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_user_search_cell.view.*

class UserSearchAdapter(private val context: Context, private val userList: List<User>) : RecyclerView.Adapter<UserSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_user_search_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.userName.text = user.name
        Picasso.get().load(user.image).placeholder(android.R.drawable.star_off).into(holder.userAvatar)

        holder.cardView.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Consts.EXTRA_USER_ID, user.id)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.txt_user_name
        val userAvatar: ImageView = itemView.img_user_avatar
        val cardView: CardView = itemView.card_user
    }

}