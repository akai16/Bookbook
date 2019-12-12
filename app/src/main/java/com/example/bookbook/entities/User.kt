package com.example.bookbook.entities

import com.example.bookbook.consts.FirebaseConsts
import com.google.firebase.firestore.DocumentId
import java.io.Serializable
import com.google.firebase.firestore.DocumentSnapshot

data class User(val name: String) : Serializable {

    @DocumentId lateinit var id: String

    var favBooks: MutableList<String> = mutableListOf()
    var wishList: MutableList<String> = mutableListOf()
    var tweetList: MutableList<Tweet> = mutableListOf()


    // Needed for Firebase Deserialization
    constructor() : this( "")


    class UserTweet(val text: String) : Serializable {
        constructor():this ("")
    }


    companion object{
        fun convertToUser(documentId: String, document: DocumentSnapshot): User{

            val userFirestore = document.data

            val user = User(userFirestore!![FirebaseConsts.USER.NAME] as String)

            user.id = documentId


            @Suppress("UNCHECKED_CAST")
            user.favBooks = userFirestore[FirebaseConsts.USER.FAV_BOOKS_LIST] as? MutableList<String> ?: mutableListOf()
            @Suppress("UNCHECKED_CAST")
            user.wishList = userFirestore[FirebaseConsts.USER.WISH_LIST] as? MutableList<String> ?: mutableListOf()


            // Get Tweet List
            val tweetList = mutableListOf<Tweet>()

            if (FirebaseConsts.USER.TWEET_LIST in userFirestore)
            (userFirestore[FirebaseConsts.USER.TWEET_LIST] as MutableList<*>).forEach {
                @Suppress("UNCHECKED_CAST")
                tweetList.add(Tweet.fromSnapshotToTweet(it as HashMap<String, Any?>))
            }

            user.tweetList = tweetList

            return user
        }
    }

}