package com.example.bookbook.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bookbook.R
import com.example.bookbook.adapters.UserTweetAdapter
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_user_profile.*


class UserProfileFragment : Fragment() {

    var currentUser: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Firebase DB Instance
        val db = FirebaseFirestore.getInstance()

        // Fetch Current User data
        // TODO: Not hardcode User ID
        db.collection(FirebaseConsts.USERS_COLLECTION).document("e3WVwUV5c9h6RK8ZVuh9")
            .get()
            .addOnSuccessListener {
                Toast.makeText(context, "User successfully fetched!!!", Toast.LENGTH_SHORT).show()

                // Display User Information
                this.currentUser = it.toObject(User::class.java)

                if (this.currentUser != null) {
                    displayUserInfomartion(this.currentUser!!)
                }
            }
            .addOnFailureListener {
                Log.d("Bookbook", "UserProfileFragment -> Failed on fetching user")
                Toast.makeText(context, "Couldn't fecth user", Toast.LENGTH_SHORT).show()
            }

    }

    override fun onStart() {
        super.onStart()

        // Button Listeners
        txt_fav_books.setOnClickListener {

            if (this.currentUser == null) {
                Toast.makeText(context, "Error on retrieving user data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(context, FavBooksActivity::class.java)
            intent.putExtra(Consts.EXTRA_USER_DATA, this.currentUser)
            startActivity(intent)
        }

    }


    fun displayUserInfomartion(user: User) {

        // Profile Name
        txt_username.text = user.name

        // Tweets List
        recycler_user_tweet.adapter = UserTweetAdapter(user.tweetList!!, context!!)
        recycler_user_tweet.layoutManager = LinearLayoutManager(context!!)
    }

}
