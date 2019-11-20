package com.example.bookbook.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookbook.R
import com.example.bookbook.adapters.FavBookAdapter
import com.example.bookbook.db.BookbookDB
import com.example.bookbook.mocks.Mocks
import kotlinx.android.synthetic.main.activity_fav_books.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_books)


        doAsync {
            val bookbookDB = BookbookDB.getDatabase(this@FavBooksActivity)
            // TODO : Mock User
            val favBookList = bookbookDB.userDAO().getUserFavBooks(1)

            uiThread {
                recycler_fav_books.adapter = FavBookAdapter(favBookList,this@FavBooksActivity)
                recycler_fav_books.layoutManager = LinearLayoutManager(this@FavBooksActivity)
            }

        }


    }
}
