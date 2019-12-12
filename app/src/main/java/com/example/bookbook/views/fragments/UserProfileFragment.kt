package com.example.bookbook.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bookbook.R
import com.example.bookbook.adapters.UserTweetAdapter
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.User
import com.example.bookbook.viewmodel.ProfileViewModel
import com.example.bookbook.views.activities.MessageActivity
import com.example.bookbook.views.activities.UserBookListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_user_profile.*
import kotlinx.android.synthetic.main.fragment_user_profile.view.*


class UserProfileFragment : Fragment() {


    // ViewModels
    private val profileViewModel : ProfileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    // Observers
    private val profileObserver = Observer<User> {
        displayUserInformation(it)
    }

    // FireBase Objects
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.profileViewModel.changeNotifier.observe(this, profileObserver)
        this.profileViewModel.fetchUserData(mAuth.uid!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        // Setting Click Listeners
        view.fab.setOnClickListener {
            startActivity(Intent(context, MessageActivity::class.java))
        }

        // Tweet Recycler View
        view.recycler_user_tweet.adapter = UserTweetAdapter(mutableListOf(), context!!)
        view.recycler_user_tweet.layoutManager = LinearLayoutManager(context)

        return view
    }


    private fun displayUserInformation(user: User) {
        // Profile Name
        txt_username.text = user.name


        //  Tweets List
        recycler_user_tweet.adapter = UserTweetAdapter(user.tweetList, context!!)

        txt_fav_books.setOnClickListener {
            val intent = Intent(context, UserBookListActivity::class.java)
            intent.putExtra(Consts.EXTRA_USER_DATA, user)
            startActivity(intent)
        }

        txt_wish_list.setOnClickListener {
            val intent = Intent(context, UserBookListActivity::class.java)
            intent.putExtra(Consts.EXTRA_USER_DATA, user)
            startActivity(intent)
        }
    }

}
