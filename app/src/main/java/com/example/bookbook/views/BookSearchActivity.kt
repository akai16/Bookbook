package com.example.bookbook.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bookbook.R
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.consts.Consts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        val call = RetrofitInitializer().bookService().getBookByID("")

        call.enqueue(object: Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                Log.d(Consts.DEBUG_TAG, "Sucesso ao baixar volume")
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Log.d(Consts.DEBUG_TAG, "Erro ao baixar volume")

            }
        })
    }
}
