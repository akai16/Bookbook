package com.example.bookbook.entities

import android.util.Log
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.google.firebase.firestore.DocumentId
import java.io.Serializable
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.getField
import java.io.ObjectInput

data class User(val name: String) : Serializable {

    @DocumentId lateinit var id: String

    var favBooks: List<String> = listOf()
    var wishList: List<String> = listOf()
    var tweetList: List<UserTweet> = listOf()


    // Needed for Firebase Deserialization
    constructor() : this( "")


    // Using as a Firebase Map
//    class UserTweet(val text: String, val bookTitle: String, val bookID: String) : Serializable {
//        // Needed for Firebase Deserialization
//        constructor() : this ("", "", "")
//    }

    class UserTweet(val text: String) : Serializable{
        constructor():this ("")
    }


    companion object{
        fun convertToUser(documentId: String, document: DocumentSnapshot): User{

            val userFirestore = document.data

            val user = User(userFirestore!![FirebaseConsts.USER_NAME] as String)

            user.id = documentId
            user.favBooks = userFirestore[FirebaseConsts.USER_FAV_BOOKS_LIST] as List<String>
            user.wishList = userFirestore[FirebaseConsts.USER_WISH_LIST] as List<String>


            // Get Tweet List
            val tweetList = mutableListOf<UserTweet>()

            (userFirestore[FirebaseConsts.USER_TWEET_LIST] as List<*>).forEach {
                tweetList.add(UserTweet(it as String))
            }

            user.tweetList = tweetList

            return user
        }
    }

}