package com.example.bookbook.entities

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class User(val name: String) : Serializable {

    @DocumentId lateinit var id: String

    var favBooks: List<String> = listOf()
    var wishList: List<String> = listOf()
    var tweetList: List<UserTweet> = listOf()


    // Needed for Firebase Deserialization
    constructor() : this( "")


    // Using as a Firebase Map
    class UserTweet(val text: String, val bookTitle: String, val bookID: String) : Serializable {
        // Needed for Firebase Deserialization
        constructor() : this ("", "", "")
    }


}