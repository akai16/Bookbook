package com.example.bookbook.entities

data class Tweet (val user: User?, val book: Book?, val text: String, val timestamp: String){

    constructor() : this(null, null, "" ,"")
}
