package com.example.bookbook.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName=Book.TABLE_NAME)
data class Book(
    @ColumnInfo(name=TITLE_COLUMN) val title: String,
    @ColumnInfo(name=DESCRIPTION_COLUMN) val description: String,
    @ColumnInfo(name=IMAGE_COLUMN) val image: String?
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=ID_COLUMN)
    var id: Int = -1


    companion object {
        const val TABLE_NAME = "book_table"
        const val ID_COLUMN = "id"
        const val TITLE_COLUMN = "title"
        const val DESCRIPTION_COLUMN = "description"
        const val IMAGE_COLUMN = "image"
    }

}