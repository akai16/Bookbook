package com.example.bookbook.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookbook.R
import com.example.bookbook.adapters.FavBookAdapter
import com.example.bookbook.mocks.Mocks
import kotlinx.android.synthetic.main.activity_fav_books.*

class FavBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_books)

        val favBookList = Mocks.USER.getFavBooks()

        recycler_fav_books.adapter = FavBookAdapter(favBookList,this)
        recycler_fav_books.layoutManager = LinearLayoutManager(this)
    }
}
