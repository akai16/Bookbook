package com.example.bookbook.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookbook.R
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_details.*
import retrofit2.Retrofit

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val book = intent.getSerializableExtra(Consts.EXTRA_BOOK_DATA) as Book

        // Populate Layout
        txt_book_title.text = book.title
        txt_book_description.text = book.description
        Picasso.get().load(book.image).into(img_book_cover)
    }
}
