package com.example.bookbook.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookbook.R
import com.example.bookbook.consts.Consts

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)


        val bookID = intent.getIntExtra(Consts.EXTRA_BOOK_ID, -1)

        if (bookID == -1) {
            Toast.makeText(this, "Erro ao exibir informações do livro.", Toast.LENGTH_SHORT).show()
            finish()
        }

        //val book = getBookById(bookID)


    }
}
