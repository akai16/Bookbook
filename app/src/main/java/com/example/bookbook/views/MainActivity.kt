package com.example.bookbook.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookbook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_fav_books.setOnClickListener  { showFavBooksActivity() }
        txt_fav_books.setOnClickListener { showFavBooksActivity() }

    }

    fun showFavBooksActivity() {
        val intent = Intent(this, FavBooksActivity::class.java)

        startActivity(intent)
    }
}
