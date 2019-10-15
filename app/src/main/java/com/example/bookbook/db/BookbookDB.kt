package com.example.bookbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookbook.db.dao.BookDAO
import com.example.bookbook.models.Book

@Database(entities = [Book::class ], version = 1)
abstract class BookbookDB : RoomDatabase() {

    abstract fun bookDAO() : BookDAO

    companion object {

        var INSTANCE: BookbookDB? = null

        fun getDatabase(ctx: Context) : BookbookDB {

            if (INSTANCE == null) {
                synchronized(BookbookDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        ctx.applicationContext,
                        BookbookDB::class.java,
                        "bookbook.db"
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }

}