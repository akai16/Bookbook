package com.example.bookbook.entities

import java.io.Serializable

data class User(val id: String, val name: String) : Serializable {

    // DocumentReference from Firestore
    lateinit var favBooks: List<String>
    var tweetList: List<Tweet>? = listOf()

    // Needed for Firebase Deserialization
    constructor() : this( "", "")

}