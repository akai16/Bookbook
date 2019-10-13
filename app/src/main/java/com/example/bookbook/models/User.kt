package com.example.bookbook.models

class User(val id: Int, val username: String, val name:String) {

    private var favBooks: ArrayList<Book> = ArrayList<Book>()
    private var currentBooks: ArrayList<Book> = ArrayList<Book>()


    fun addFavBook(book: Book) {
        this.favBooks.add(book)
    }

    fun getFavBooks(): List<Book> {
        return this.favBooks
    }

    fun removeFavBook(book: Book) {
        this.favBooks.remove(book)
    }

    fun addToCurrentBooks(book: Book) {
        this.currentBooks.add(book)
    }

    fun getCurrentBooks(): List<Book> {
        return this.currentBooks
    }

    fun removeToCurrentBooks(book: Book) {
        this.currentBooks.remove(book)
    }

}