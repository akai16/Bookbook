package com.example.bookbook.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookbook.adapters.BookSearchAdapter
import com.example.bookbook.api.BookListResponse
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel(private var bookQuery: String = "") : ViewModel() {

    val changeNotifier = MutableLiveData<List<Book>>()
    private val bookService = RetrofitInitializer().bookService()

    fun searchBook(bookQuery: String) {

        val call = bookService!!.searchBooks(bookQuery)

        call.enqueue(object : Callback<BookListResponse> {
            override fun onResponse(
                call: Call<BookListResponse>,
                response: Response<BookListResponse>
            ) {
                val bookListResponse = response.body()
                val mutableBookList = mutableListOf<Book>()

                bookListResponse!!.items.forEach {
                    val book = it.getBookObject()
                    mutableBookList.add(book)
                }

                this@BooksViewModel.changeNotifier.value = mutableBookList
            }

            override fun onFailure(call: Call<BookListResponse>, t: Throwable) {
                Log.d(Consts.DEBUG_TAG, "Erro ao baixar volume")
            }
        })

    }

}