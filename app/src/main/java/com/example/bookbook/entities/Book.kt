package com.example.bookbook.entities

data class Book(val title: String, val author: String, val description: String, val image: String?) {

    // Needed for Firebase Deserialization
    constructor() : this( "", "", "", null)
}