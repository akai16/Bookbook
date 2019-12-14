package com.example.bookbook.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.Tweet
import com.example.bookbook.entities.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.lang.reflect.GenericArrayType
import java.lang.reflect.Type

class ProfileViewModel : ViewModel() {

    var changeNotifier = MutableLiveData<User>()
    var tweetNotifier = MutableLiveData<List<Tweet>>()

    private val db = FirebaseFirestore.getInstance()


    fun fetchUserData(userID: String) {

        db.collection(FirebaseConsts.USERS_COLLECTION).document(userID)
            .get()
            .addOnSuccessListener {
                changeNotifier.value = User.convertToUser(userID, it)
            }
            .addOnFailureListener {
                Log.d(Consts.DEBUG_TAG, "ProfileViewModel -> Failed on fetching user")
            }
    }

    fun fetchUserTweets(userID: String) {
        db.collection(FirebaseConsts.USERS_COLLECTION).document(userID)
            .get()
            .addOnSuccessListener {
                val user = User.convertToUser(userID, it)
                val tweetList = user.tweetList
                tweetNotifier.value = tweetList
            }
            .addOnFailureListener {
                Log.d(Consts.DEBUG_TAG, "ProfileViewModel -> Failed on fetching user tweets")
            }
    }

}