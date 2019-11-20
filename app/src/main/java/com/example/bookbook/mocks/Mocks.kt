package com.example.bookbook.mocks

import com.example.bookbook.entities.Author
import com.example.bookbook.entities.Book
import com.example.bookbook.entities.User

object Mocks {

    val USER = User("capivara12", "Capivara Ensandecida")

    val AUTHOR_1 = Author("Robert Greene")
    val AUTHOR_2 = Author("Ernest Hemingway")


    // AUTHOR_1 Books
    val BOOK_1 =
        Book("The 48 Laws of Power", "Descriçao do livro...", null)
    val BOOK_2 = Book("Mastery", "", null)


    // AUTHOR_2 Books
    val BOOK_3 = Book(
        "The Old Man and The Sea",
        "Descriçao do livro...",
        null
    )


    // User Data
    fun init_user_data() {

    }





}
