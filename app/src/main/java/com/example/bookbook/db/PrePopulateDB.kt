package com.example.bookbook.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bookbook.entities.Book

class PrePopulateDB : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        Book("48 Laws of Power", "Ipsom loren...", null)

    }
}