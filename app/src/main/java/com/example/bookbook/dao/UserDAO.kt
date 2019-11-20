package com.example.bookbook.dao

import androidx.room.*
import com.example.bookbook.entities.Book
import com.example.bookbook.entities.FavBooksEntity
import com.example.bookbook.entities.ReadBooksEntity
import com.example.bookbook.entities.User

@Dao
interface UserDAO {

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.ID_COLUMN} = :userID")
    fun getUserByID(userID: Int): User

    @Query(
        "SELECT Book.* " +
        " FROM ${Book.TABLE_NAME} as Book " +
        "LEFT JOIN ${FavBooksEntity.TABLE_NAME} as FavBook " +
        "ON Book.${Book.ID_COLUMN} = FavBook.${FavBooksEntity.BOOK_ID_COLUMN} " +
        "WHERE ${FavBooksEntity.BOOK_ID_COLUMN} = :userID "
    )
    fun getUserFavBooks(userID: Int): List<Book>

    @Query(
        "SELECT Book.* FROM ${Book.TABLE_NAME} as Book " +
        "LEFT JOIN ${ReadBooksEntity.TABLE_NAME} as ReadBook " +
        "ON Book.${Book.ID_COLUMN} = ReadBook.${ReadBooksEntity.BOOK_ID_COLUMN} " +
        "WHERE ReadBook.${ReadBooksEntity.USER_ID_COLUMN} = :userID "
    )
    fun getUserReadBooks(userID: Int): List<Book>


    @Insert(entity = User::class, onConflict = OnConflictStrategy.ABORT)
    fun createUser(newUser: User)

    @Insert(entity = FavBooksEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addBookToUserFavBooks(favBookRow: FavBooksEntity)

    @Insert(entity = ReadBooksEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addBookToUserReadBooks(readBookRow: ReadBooksEntity)
}