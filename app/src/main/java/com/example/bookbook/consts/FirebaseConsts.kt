package com.example.bookbook.consts

object FirebaseConsts {

    const val USERS_COLLECTION = "users"
    const val BOOKS_COLLECTION = "books"


    // User Document Fields
    object USER {
        const val NAME = "name"
        const val FAV_BOOKS_LIST = "favBooks"
        const val WISH_LIST = "wishList"
        const val TWEET_LIST = "tweetList"
        const val NAME_KEYWORDS = "name_keywords"
    }

    object TWEET {
        const val ID = "id"
        const val USER_ID = "userID"
        const val BOOK_ID = "bookID"
        const val TEXT = "text"
        const val TIMESTAMP = "timestamp"
    }

}