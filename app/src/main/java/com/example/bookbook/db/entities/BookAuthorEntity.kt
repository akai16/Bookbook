package com.example.bookbook.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.bookbook.models.Author
import com.example.bookbook.models.Book

@Entity(tableName = "book_author_table", primaryKeys = ["bookID", "authorID"])
class BookAuthorEntity {

    @ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["book_id"])
    var bookID: Int = -1

    @ForeignKey(entity = Author::class, parentColumns = ["id"], childColumns = ["author_id"])
    var authorID: Int = -1
}