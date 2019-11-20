package com.example.bookbook.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = BookAuthorEntity.TABLE_NAME, primaryKeys = [
    BookAuthorEntity.BOOK_ID_COLUMN,
    BookAuthorEntity.AUTHOR_ID_COLUMN
])
class BookAuthorEntity {

    @ForeignKey(entity = Book::class, parentColumns = [Book.ID_COLUMN], childColumns = [BOOK_ID_COLUMN])
    @ColumnInfo(name=BOOK_ID_COLUMN)
    var bookID: Int = -1

    @ForeignKey(entity = Author::class, parentColumns = [Author.ID_COLUMN], childColumns = [AUTHOR_ID_COLUMN])
    @ColumnInfo(name=AUTHOR_ID_COLUMN)
    var authorID: Int = -1

    companion object {
        const val TABLE_NAME = "book_author_table"
        const val BOOK_ID_COLUMN = "book_id"
        const val AUTHOR_ID_COLUMN = "author_id"

    }
}