package com.example.bookbook.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentSnapshot
import java.io.Serializable

data class Book(val title: String, val author: String, val description: String, val image: String?)  :
    Serializable {

    @DocumentId lateinit var id: String

    // Needed for Firebase Deserialization
    constructor() : this( "", "", "", null)

}