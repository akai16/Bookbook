package com.example.bookbook.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bookbook.R
import com.example.bookbook.adapters.UserBookListAdapter
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.entities.Book
import com.example.bookbook.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.fragment_user_book_list.*
import kotlinx.android.synthetic.main.fragment_user_book_list.view.*

class UserBookListFragment : Fragment() {

    private val EXTRA_BOOK_LIST = "booklist"

    // View Models
    private val listViewModel : BooksViewModel by lazy {
        ViewModelProviders.of(this).get(BooksViewModel::class.java)
    }

    // Observers
    private val listObserver = Observer<List<Book>> {
        recycler_user_book_list.adapter = UserBookListAdapter(context!!, it)
    }

    private val userBookList: MutableList<Book> = mutableListOf()
    private var bookIDList = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.bookIDList = arguments?.getStringArrayList(EXTRA_BOOK_LIST) as List<String>

        this.listViewModel.changeNotifier.observe(this, listObserver)
        this.listViewModel.searchBookList(bookIDList)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_book_list, container, false)

        view.recycler_user_book_list.adapter = UserBookListAdapter(context!!, this.userBookList)
        view.recycler_user_book_list.layoutManager = LinearLayoutManager(context!!)

        return view
    }

    companion object {

        fun newInstance(bookList: List<String>): UserBookListFragment {
            val frag = UserBookListFragment()
            val bundle = Bundle()

            bundle.putStringArrayList(frag.EXTRA_BOOK_LIST, ArrayList(bookList))
            frag.arguments = bundle

            return frag
        }
    }


}
