package com.example.bookbook.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookbook.entities.Author
import com.example.bookbook.entities.BookAuthorEntity
import com.example.bookbook.entities.Book

@Dao
interface BookDAO {

    @Query(
        "SELECT Book.* FROM ${Book.TABLE_NAME} as Book " +
              "WHERE ${Book.ID_COLUMN} IN ( " +
                    "SELECT bat.${BookAuthorEntity.BOOK_ID_COLUMN} " +
                    "FROM ${BookAuthorEntity.TABLE_NAME} as bat, ${Author.TABLE_NAME} as at " +
                    "WHERE at.${Author.NAME_COLUMN} == :author " +
                    "AND at.${Author.ID_COLUMN} == bat.${BookAuthorEntity.AUTHOR_ID_COLUMN} " +
              ")"
    )
    fun getBooksByAuthor(author: String): List<Book>

    @Query("SELECT * FROM ${Book.TABLE_NAME} WHERE ${Book.ID_COLUMN} = :id")
    fun getBookByID(id: Int): Book

    @Query("SELECT * FROM ${Book.TABLE_NAME} WHERE ${Book.TITLE_COLUMN} LIKE :title")
    fun getBookByTitle(title: String): List<Book>

    @Insert(entity = Book::class, onConflict = OnConflictStrategy.REPLACE)
    fun createBook(book: Book)
}
