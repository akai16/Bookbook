package com.example.bookbook.views

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_fav_books.*

class FavBooksActivity : AppCompatActivity() {

    var currentUser: User? = null
    var userFavBooks: MutableList<Book> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_books)


        supportActionBar?.title = "Favorite Books"


        // Firebase DB Instance
        val db = FirebaseFirestore.getInstance()

        this.currentUser = intent.getSerializableExtra(Consts.EXTRA_USER_DATA) as User?

        // Finish activity in case User data was not correctly retrieved
        if (this.currentUser == null) {
            Toast.makeText(this, "Error on retrieving user data", Toast.LENGTH_SHORT).show()
            Log.d(Consts.DEBUG_TAG, "FavBooksActivity -> Error on retrieving serializable user data")

            finish()
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
            val favAdapter = FavBookAdapter(this.userFavBooks, this)
            recycler_fav_books.adapter = favAdapter
            recycler_fav_books.layoutManager = LinearLayoutManager(this)
        }

    }
}
