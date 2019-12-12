package com.example.bookbook.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.User
import com.google.firebase.firestore.FirebaseFirestore

class ProfileViewModel : ViewModel() {

    var changeNotifier = MutableLiveData<User>()
    var tweetNotifier = MutableLiveData<User.UserTweet>()

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

            }
            .addOnFailureListener {
                Log.d(Consts.DEBUG_TAG, "ProfileViewModel -> Failed on fetching user tweets")
            }
    }

}