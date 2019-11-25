package com.example.bookbook.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bookbook.R
import com.example.bookbook.adapters.FavBookAdapter
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Book
import com.example.bookbook.entities.User
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_fav_books.*

class FavBooksFragment : Fragment() {

    var currentUser: User? = null
    var userFavBooks: MutableList<Book> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.actionBar?.title = resources.getString(R.string.favorite_books)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_books, container, false)
    }


    override fun onStart() {
        super.onStart()

        // Firebase DB Instance
        val db = FirebaseFirestore.getInstance()

        this.currentUser = activity!!.intent.getSerializableExtra(Consts.EXTRA_USER_DATA) as User?

        // Finish activity in case User data was not correctly retrieved
        if (this.currentUser == null) {
            Toast.makeText(context, "Error on retrieving user data", Toast.LENGTH_SHORT).show()
            Log.d(Consts.DEBUG_TAG, "FavBooksActivity -> Error on retrieving serializable user data")
        }


        // Create List<Task> for retrieving book information
        val taskList: MutableList<Task<DocumentSnapshot>> = mutableListOf()
        this.currentUser!!.favBooks.forEach{
            val task = db.document(it).get()

            taskList.add(task)
        }

        // Get all books from User fav list and then, render recycler view
        Tasks.whenAllComplete(taskList).addOnSuccessListener {
            it.forEach {
                val book = (it.result as DocumentSnapshot).toObject(Book::class.java)

                if (book != null) {
                    this.userFavBooks.add(book)
                }
            }

            // Rendering Recycler View
            val favAdapter = FavBookAdapter(this.userFavBooks, context!!)
            recycler_fav_books.adapter = favAdapter
            recycler_fav_books.layoutManager = LinearLayoutManager(context!!)
        }

    }

}
