package com.example.bookbook.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.bookbook.models.Book
import com.example.bookbook.models.User

@Entity(tableName = "favorite_books", primaryKeys = ["userID", "bookID"])
class FavBooksEntity {

    @ForeignKey(entity = User::class, parentColumns =["id"], childColumns = ["user_id"])
    var userID: Int = 0

    @ForeignKey(entity = Book::class, parentColumns =["id"], childColumns = ["book_id"])
    var bookID: Int = 0
}