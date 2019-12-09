package com.example.bookbook.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bookbook.R
import com.example.bookbook.adapters.UserBookListAdapter
import com.example.bookbook.api.RetrofitInitializer
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import com.example.bookbook.entities.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_wish_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class WishListFragment : Fragment() {

    var currentUser: User? = null
    var userWishList: MutableList<Book> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.actionBar?.title = resources.getString(R.string.wishlist)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wish_list, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Retrofit
        val bookService = RetrofitInitializer().bookService()

        // Firebase DB Instance
        val db = FirebaseFirestore.getInstance()

        this.currentUser = activity!!.intent.getSerializableExtra(Consts.EXTRA_USER_DATA) as User?

        // Finish activity in case User data was not correctly retrieved
        if (this.currentUser == null) {
            Toast.makeText(context, "Error on retrieving user data", Toast.LENGTH_SHORT).show()
            Log.d(
                Consts.DEBUG_TAG,
                "WishListsFragment -> Error on retrieving serializable user data"
            )
        }


        doAsync {
            this@WishListFragment.currentUser!!.wishList.forEach {

                val bookCall = bookService.getBookByID(it)
                val bookResponse = bookCall.execute().body()
                val book = bookResponse!!.getBookObject()

                userWishList.add(book)
            }

            uiThread {
                val favAdapter = UserBookListAdapter(this@WishListFragment.userWishList, context!!)
                recycler_wish_list.adapter = favAdapter
                recycler_wish_list.layoutManager = LinearLayoutManager(context!!)
            }

        }

    }
}
