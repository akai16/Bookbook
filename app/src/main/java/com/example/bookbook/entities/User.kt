package com.example.bookbook.entities

import com.example.bookbook.consts.FirebaseConsts
import com.google.firebase.firestore.DocumentId
import java.io.Serializable
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.getField

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
        fun convertToUserTweet(documet: DocumentSnapshot){

            val tweetList = documet.getField<List<String>>(FirebaseConsts.USER_TWEET_LIST)
            val asdsada = 0
//            for ( str in tweetList){
//
//            }

        }
    }

}