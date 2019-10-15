package com.example.bookbook.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.bookbook.models.Book

@Dao
interface BookDAO {

    @Query("SELECT * FROM book_table WHERE id IN (SELECT bat.bookID FROM book_author_table as bat, author_table as at WHERE at.name == :author AND at.id == bat.authorID)")
    fun getBooksByAuthor(author: String): List<Book>

    @Query("SELECT * FROM book_table WHERE id == :id")
    fun getBookByID(id: Int): Book

    @Query("SELECT * FROM book_table WHERE title LIKE :title")
    fun getBookByTitle(title: String): List<Book>
}
