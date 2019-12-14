package com.example.bookbook.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {
    @GET("volumes/{id}")
    fun getBookByID(
        @Path("id") id: String
    ): Call<BookResponse>


    @GET("volumes?maxResults=10&printType=books")
    fun searchForBooks(@Query("q") query: String): Call<BookListResponse>
}