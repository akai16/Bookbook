package com.example.bookbook.entities

import com.google.firebase.firestore.DocumentId

data class Author(val name: String) {

    @DocumentId lateinit var id: String

    // Needed for Firebase Deserialization
    constructor() : this( "")
}