package com.example.bookbook.entities

import com.google.firebase.firestore.DocumentId

data class Book(val title: String, val author: String, val description: String, val image: String?) {

    @DocumentId lateinit var id: String

    // Needed for Firebase Deserialization
    constructor() : this( "", "", "", null)

}