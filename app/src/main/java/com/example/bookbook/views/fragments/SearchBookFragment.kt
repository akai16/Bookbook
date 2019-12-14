package com.example.bookbook.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookbook.R
import com.example.bookbook.adapters.BookSearchAdapter
import com.example.bookbook.entities.Book
import com.example.bookbook.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.fragment_search_book.*
import kotlinx.android.synthetic.main.fragment_search_book.view.*

class SearchBookFragment : Fragment() {

    // ViewModel
    private val bookViewModel: BooksViewModel by lazy {
        ViewModelProviders.of(this).get(BooksViewModel::class.java)
    }

    private val changeObserver = Observer<List<Book>> {
        recycler_search_book.adapter = BookSearchAdapter(context!!, it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookViewModel.changeNotifier.observe(this, changeObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_book, container, false)

        // Set Recycler View
        view.recycler_search_book.adapter = BookSearchAdapter(context!!, listOf())
        view.recycler_search_book.layoutManager = LinearLayoutManager(context)

        // Setting Search Btn Listener
        view.btn_search_book.setOnClickListener {
            bookViewModel.searchForBooks(view.edit_book_search.text.toString())
        }

        return view
    }



}