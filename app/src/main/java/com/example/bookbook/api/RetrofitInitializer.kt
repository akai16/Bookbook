package com.example.bookbook.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    fun bookService() = retrofit.create(BookService::class.java)


    private val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}