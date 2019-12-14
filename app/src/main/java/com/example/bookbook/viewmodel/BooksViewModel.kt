package com.example.bookbook.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BooksViewModel : ViewModel() {

    val changeNotifier = MutableLiveData<List<Book>>()
    private val bookService = RetrofitInitializer().bookService()

    fun searchBookList(bookIDList: List<String>) {

        doAsync {
            val mutableBookList = mutableListOf<Book>()

            bookIDList.forEach {
                val call = bookService!!.getBookByID(it)
                val response = call.execute()

                if (!response.isSuccessful) {
                    Log.d(Consts.DEBUG_TAG, "BooksViewModel -> searchBookList -> Falhou ao baixar o livro")
                }
                else {
                    val bookResponse = response.body()
                    val book = bookResponse?.getBookObject()!!

                    mutableBookList.add(book)
                }
            }

            uiThread {
                this@BooksViewModel.changeNotifier.value = mutableBookList
            }
        }

    }

    fun searchForBooks(query: String) {
        doAsync {
            val mutableBookList = mutableListOf<Book>()

            val call = bookService!!.searchForBooks(query)
            val response = call.execute()

            if (!response.isSuccessful) {
                Log.d(Consts.DEBUG_TAG, "BooksViewModel -> searchBookID -> Falhou ao baixar o livro")
            }
            else {
                val bookListResponse = response.body()
                bookListResponse?.items?.forEach {
                    mutableBookList.add(it.getBookObject())
                }
            }

            uiThread {
                this@BooksViewModel.changeNotifier.value = mutableBookList
            }
        }


    }

}