package com.example.bookbook.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bookbook.R
import com.example.bookbook.adapters.UserBookListAdapter
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.entities.Book
import com.example.bookbook.views.activities.UserBookListActivity
import kotlinx.android.synthetic.main.activity_user_book_list.*
import kotlinx.android.synthetic.main.fragment_user_book_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception

class UserBookListFragment : Fragment() {

    private val userFavBooks: MutableList<Book> = mutableListOf()
    private val bookService = RetrofitInitializer().bookService()
    private val EXTRA_BOOK_LIST = "booklist"

    private var bookList = listOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.bookList = arguments?.getStringArrayList(EXTRA_BOOK_LIST) as List<String>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_book_list, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        doAsync {
            bookList.forEach {

                val bookCall = bookService.getBookByID(it)
                val bookResponse = bookCall.execute().body()
                val book = bookResponse!!.getBookObject()

                userFavBooks.add(book)
            }

            uiThread {
                val favAdapter = UserBookListAdapter(context!!, this@UserBookListFragment.userFavBooks)
                recycler_user_book_list.adapter = favAdapter
                recycler_user_book_list.layoutManager = LinearLayoutManager(context!!)
            }
        }
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
