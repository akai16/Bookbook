package com.example.bookbook.entities

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class Author(val name: String) {

    @DocumentId lateinit var id: String

    // Needed for Firebase Deserialization
    constructor() : this( "")
}