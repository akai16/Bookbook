package com.example.bookbook.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbook.R
import com.example.bookbook.entities.User
import kotlinx.android.synthetic.main.recycler_tweet_cell.view.*

class UserTweetAdapter(private val tweetList: List<User.UserTweet>, private val ctx: Context) : RecyclerView.Adapter<UserTweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.recycler_tweet_cell, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = tweetList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val tweet = tweetList[position]

        holder.tweetText.text = tweet.text
        holder.tweetBookTitle.text = tweet.bookTitle
//        holder.tweetTimestamp.text = tweet.timestamp

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tweetText = itemView.tweet_txt
        val tweetBookTitle = itemView.tweet_book_title
//        val tweetTimestamp = itemView.tweet_timestamp
    }

}