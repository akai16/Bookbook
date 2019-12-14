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
    var image: String? = null

    // Needed for Firebase Deserialization
    constructor() : this( "")

    companion object{
        fun convertToUser(documentId: String, document: DocumentSnapshot): User{

            val user = document.toObject(User::class.java)
            user!!.id = documentId
            return user
        }
    }

}