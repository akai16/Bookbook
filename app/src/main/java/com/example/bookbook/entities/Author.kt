package com.example.bookbook.entities


data class Author(val name: String) {

    // Needed for Firebase Deserialization
    constructor() : this( "")
}