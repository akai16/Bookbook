package com.example.bookbook.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = Author.TABLE_NAME)
class Author(@ColumnInfo(name=NAME_COLUMN) val name: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= ID_COLUMN)
    var id: Int = -1

    companion object {
        const val TABLE_NAME = "author_table"
        const val ID_COLUMN = "id"
        const val NAME_COLUMN = "name"
    }

}