package com.example.bookbook.models

class Author (val id: Int, val name: String) {

    private var books: ArrayList<Book> = ArrayList<Book>()

    fun addBook(book: Book) {
        this.books.add(book)
    }

}