package com.example.bookbook.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookbook.R
import com.example.bookbook.adapters.BookSearchAdapter
import com.example.bookbook.api.BookListResponse
import com.example.bookbook.api.BookService
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import kotlinx.android.synthetic.main.fragment_book_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookSearchFragment : Fragment() {

    lateinit var bookService: BookService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookService = RetrofitInitializer().bookService()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_search_book.setOnClickListener {
            searchForBook(edit_book_search.text.toString())
        }
    }


    private fun searchForBook(query: String) {

        val call = bookService.searchBooks(query)

        call.enqueue(object: Callback<BookListResponse> {
            override fun onResponse(call: Call<BookListResponse>, response: Response<BookListResponse>) {
                val bookResponse = response.body()
                val bookList =  mutableListOf<Book>()

                bookResponse?.items?.forEach {

                    val book = it.getBookObject()

                    // Add to Search Response List
                    bookList.add(book)
                }

                this@BookSearchFragment.recycler_search_book.adapter = BookSearchAdapter(context!!, bookList)
                this@BookSearchFragment.recycler_search_book.layoutManager = LinearLayoutManager(context)
            }

            override fun onFailure(call: Call<BookListResponse>, t: Throwable) {
                Log.d(Consts.DEBUG_TAG, "Erro ao baixar volume")
            }
        })


    }
}
