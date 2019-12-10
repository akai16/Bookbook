package com.example.bookbook.views.fragments

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
import com.example.bookbook.views.activities.MessageActivity
import com.example.bookbook.views.activities.UserBookListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_user_profile.*


class UserProfileFragment : Fragment() {

    private var currentUser: User? = null

    // FireBase Objects
    private var db: FirebaseFirestore? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FireBase Instances
        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        // Fetch Current User data
        fetchUserData()

    }


    private fun fetchUserData() {
        db!!.collection(FirebaseConsts.USERS_COLLECTION).document(mAuth!!.currentUser!!.uid)
            .get()
            .addOnSuccessListener {
                Toast.makeText(context, "User successfully fetched!!!", Toast.LENGTH_SHORT).show()

                // Display User Information
                this.currentUser = it.toObject(User::class.java)

                if (this.currentUser != null) {
                    displayUserInformation(this.currentUser!!)
                }
            }
            .addOnFailureListener {
                Log.d("Bookbook", "UserProfileFragment -> Failed on fetching user")
                Toast.makeText(context, "Couldn't fecth user", Toast.LENGTH_SHORT).show()
            }
    }

    private fun displayUserInformation(user: User) {
        // Profile Name
        txt_username.text = user.name

        // Tweets List
        recycler_user_tweet.adapter = UserTweetAdapter(user.tweetList, context!!)
        recycler_user_tweet.layoutManager = LinearLayoutManager(context!!)

        txt_fav_books.setOnClickListener {

            if (this.currentUser == null) {
                Toast.makeText(context, "Error on retrieving user data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(context, UserBookListActivity::class.java)
            intent.putExtra(Consts.EXTRA_USER_DATA, this.currentUser)
            startActivity(intent)
        }

        txt_wish_list.setOnClickListener {

            if (this.currentUser == null) {
                Toast.makeText(context, "Error on retrieving user data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(context, UserBookListActivity::class.java)
            intent.putExtra(Consts.EXTRA_USER_DATA, this.currentUser)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        fab.setOnClickListener{
            val intent = Intent(context, MessageActivity::class.java)
            startActivity(intent)
        }
    }


}
