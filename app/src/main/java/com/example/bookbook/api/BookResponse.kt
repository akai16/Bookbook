package com.example.bookbook.api

import com.example.bookbook.entities.Book

class BookResponse {

    lateinit var id: String
    lateinit var volumeInfo: VolumeInfo


    inner class VolumeInfo {
        lateinit var title: String
        lateinit var imageLinks: ImageLinks
        var description: String? = ""
    }


    inner class ImageLinks {
        lateinit var smallThumbnail: String
        lateinit var thumbnail: String
    }


    fun getBookObject(): Book {
        val book = Book(
            this.volumeInfo.title,
            "",
            this.volumeInfo.description ?: "" ,
            this.volumeInfo.imageLinks.smallThumbnail
        )

        book.id = this.id

        return book
    }
}