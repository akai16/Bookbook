package com.example.bookbook.models

class Book(val id: Int, val authors: List<Author>, val title: String, val description: String, val image: String?) {

    init {
        authors.forEach {
            it.addBook(this)
        }
    }

}