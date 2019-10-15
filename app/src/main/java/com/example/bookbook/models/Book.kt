package com.example.bookbook.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="book_table")
data class Book(val title: String, val description: String, val image: String?) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1

}