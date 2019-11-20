package com.example.bookbook.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookbook.entities.Author
import com.example.bookbook.entities.BookAuthorEntity

@Dao
interface AuthorDAO {

    @Query(
        "SELECT * FROM ${Author.TABLE_NAME} " +
            "WHERE ${Author.ID_COLUMN} = :authorID")
    fun getAuthorByID(authorID: Int) : Author


    @Query("SELECT Author.* " +
            "FROM ${Author.TABLE_NAME} AS Author " +
            "LEFT JOIN ${BookAuthorEntity.TABLE_NAME} AS BookAuthor " +
            "ON Author.${Author.ID_COLUMN} = BookAuthor.${BookAuthorEntity.AUTHOR_ID_COLUMN} " +
            "AND BookAuthor.${BookAuthorEntity.AUTHOR_ID_COLUMN} = :bookID ")
    fun getAuthorsByBookID(bookID: Int) : List<Author>


    @Insert(entity = Author::class, onConflict = OnConflictStrategy.ABORT)
    fun createAuthor(author: Author)

}