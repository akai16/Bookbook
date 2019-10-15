package com.example.bookbook.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "author_table")
class Author (val name: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1

}