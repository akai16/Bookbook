package com.example.bookbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookbook.dao.AuthorDAO
import com.example.bookbook.dao.BookDAO
import com.example.bookbook.dao.UserDAO
import com.example.bookbook.entities.BookAuthorEntity
import com.example.bookbook.entities.FavBooksEntity
import com.example.bookbook.entities.ReadBooksEntity
import com.example.bookbook.entities.Author
import com.example.bookbook.entities.Book
import com.example.bookbook.entities.User

@Database(entities = [
    User::class,
    Author::class,
    Book::class,
    BookAuthorEntity::class,
    FavBooksEntity::class,
    ReadBooksEntity::class
], version = 1, exportSchema = false)
abstract class BookbookDB : RoomDatabase() {

    abstract fun authorDAO() : AuthorDAO
    abstract fun bookDAO() : BookDAO
    abstract fun userDAO() : UserDAO

    companion object {

        private var INSTANCE: BookbookDB? = null

        fun getDatabase(ctx: Context) : BookbookDB {

            if (INSTANCE == null) {
                synchronized(BookbookDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        ctx.applicationContext,
                        BookbookDB::class.java,
                        "bookbook.db"
                    ).addCallback(PrePopulateDB())
                        .build()
                }
            }

            return INSTANCE!!
        }
    }

}