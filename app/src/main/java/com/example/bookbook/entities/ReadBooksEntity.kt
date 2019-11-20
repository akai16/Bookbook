package com.example.bookbook.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = ReadBooksEntity.TABLE_NAME, primaryKeys = [
    ReadBooksEntity.USER_ID_COLUMN,
    ReadBooksEntity.BOOK_ID_COLUMN
])
class ReadBooksEntity {

    @ForeignKey(entity = User::class, parentColumns = [User.ID_COLUMN], childColumns = [USER_ID_COLUMN])
    @ColumnInfo(name= USER_ID_COLUMN)
    var userID: Int = -1

    @ForeignKey(entity = Book::class, parentColumns = [Book.ID_COLUMN], childColumns = [BOOK_ID_COLUMN])
    @ColumnInfo(name= BOOK_ID_COLUMN)
    var bookID: Int = -1

    companion object {
        const val TABLE_NAME = "read_books"
        const val USER_ID_COLUMN = "user_id"
        const val BOOK_ID_COLUMN = "book_id"
    }
}