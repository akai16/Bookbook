package com.example.bookbook.mocks

import com.example.bookbook.models.Author
import com.example.bookbook.models.Book
import com.example.bookbook.models.User

object Mocks {

    val USER = User(1, "capivara12", "CapivaraDesorientada")

    val AUTHOR_1 = Author(0, "Robert Greene")
    val AUTHOR_2 = Author(1, "Ernest Hemingway")


    // AUTHOR_1 Books
    val BOOK_1 = Book(0, listOf(AUTHOR_1), "The 48 Laws of Power", "", null)
    val BOOK_2 = Book(1, listOf(AUTHOR_1), "Mastery", "", null)


    // AUTHOR_2 Books
    val BOOK_3 = Book(2, listOf(AUTHOR_2), "The Old Man and The Sea", "", null)



    // User Data
    fun init_user_data() {
        USER.addFavBook(BOOK_1)
        USER.addFavBook(BOOK_3)
    }





}
