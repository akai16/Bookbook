package com.example.bookbook.views.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bookbook.R
import com.example.bookbook.adapters.UserTweetAdapter
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.Tweet
import com.example.bookbook.entities.User
import com.example.bookbook.viewmodel.ProfileViewModel
import com.example.bookbook.views.activities.MessageActivity
import com.example.bookbook.views.activities.UserBookListActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_profile.*
import kotlinx.android.synthetic.main.fragment_user_profile.view.*

class UserProfileFragment : Fragment() {

    private val TWEET_REQUEST_ACTIVITY = 1
    private val EXTRA_USER_ID = "userID"
    private var userID: String? = null

    private val currentUser = FirebaseAuth.getInstance().uid!!

    // ViewModels
    private val profileViewModel : ProfileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    // Observers
    private val profileObserver = Observer<User> { displayUserInformation(it) }
    private val tweetObserver = Observer<List<Tweet>> { updateTweetList(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.userID = arguments!!.getString(EXTRA_USER_ID) ?: "Null"

        this.profileViewModel.changeNotifier.observe(this, profileObserver)


        this.profileViewModel.tweetNotifier.observe(this, tweetObserver)
    }


    override fun onStart() {
        super.onStart()
        this.profileViewModel.fetchUserData(this.userID!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        // Setting Click Listeners
        view.fab.setOnClickListener {
            val intent = Intent(context, MessageActivity::class.java)
            startActivityForResult(intent, this.TWEET_REQUEST_ACTIVITY)
        }

        if (this.userID != this.currentUser) {
            view.fab.hide()
        }

        // Tweet Recycler View
        view.recycler_user_tweet.adapter = UserTweetAdapter(mutableListOf(), context!!)
        view.recycler_user_tweet.layoutManager = LinearLayoutManager(context)

        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == this.TWEET_REQUEST_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                this.profileViewModel.fetchUserTweets(this.userID!!)
            }
        }
    }


    private fun displayUserInformation(user: User) {

        // Profile Name
        txt_username.text = user.name

        // User Image
        Picasso.get().load(user.image).placeholder(android.R.drawable.star_off).into(img_user_avatar)

        //  Tweets List
        recycler_user_tweet.adapter = UserTweetAdapter(user.tweetList, context!!)

        txt_fav_books.setOnClickListener {
            val intent = Intent(context, UserBookListActivity::class.java)
            intent.putExtra(Consts.EXTRA_USER_DATA, user)
            intent.putExtra(UserBookListActivity.EXTRA_SCREEN, UserBookListActivity.EXTRA_FAV_BOOK_SCREEN)
            startActivity(intent)
        }

        txt_wish_list.setOnClickListener {
            val intent = Intent(context, UserBookListActivity::class.java)
            intent.putExtra(Consts.EXTRA_USER_DATA, user)
            intent.putExtra(UserBookListActivity.EXTRA_SCREEN, UserBookListActivity.EXTRA_WISH_LIST_SCREEN)
            startActivity(intent)
        }
    }

    private fun updateTweetList(tweetList: List<Tweet>) {

        val tweetAdapter: UserTweetAdapter = recycler_user_tweet.adapter as UserTweetAdapter

        tweetAdapter.clearAll()
        tweetAdapter.addNewList(tweetList)
    }


    companion object {

        fun getInstance(userID: String): UserProfileFragment {
            val frag = UserProfileFragment()
            val bundle = Bundle()

            bundle.putString(frag.EXTRA_USER_ID, userID)
            frag.arguments = bundle
            return frag
        }
    }

}
