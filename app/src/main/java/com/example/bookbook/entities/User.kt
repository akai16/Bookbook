package com.example.bookbook.entities

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class User(val name: String) : Serializable {

    @DocumentId lateinit var id: String

    lateinit var favBooks: List<String>
    var tweetList: List<UserTweet> = listOf()


    // Needed for Firebase Deserialization
    constructor() : this( "")



    // Using as a Firebase Map
    class UserTweet(val text: String, val bookTitle: String, val bookID: String) : Serializable {
        // Needed for Firebase Deserialization
        constructor() : this ("", "", "")
    }


}