package com.example.bookbook.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET("volumes/{id}")
    fun getBookByID(
        @Path("id") id: String
    ): Call<BookResponse>
}