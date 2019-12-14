package com.example.bookbook.entities

import com.example.bookbook.consts.FirebaseConsts
import java.io.Serializable
import java.time.Instant
import java.util.*
import kotlin.collections.HashMap

class Tweet : Serializable{

    var id: String = ""
    var bookID: String = ""
    var text: String = ""
    var date: Date? = null


    companion object {
        fun fromSnapshotToTweet(tweetMap: HashMap<String, Any?>): Tweet {

            val tweet = Tweet()
            tweet.text = tweetMap[FirebaseConsts.TWEET.TEXT] as? String ?: ""
            return tweet
        }

    }

}
