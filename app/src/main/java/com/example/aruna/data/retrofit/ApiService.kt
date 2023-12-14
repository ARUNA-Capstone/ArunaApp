package com.example.aruna.data.retrofit

import com.example.aruna.data.response.ArticleByIdResponse
import com.example.aruna.data.response.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/search")
    fun searchArticles(
        @Query("q") id: String,
    ): Call<ArticleResponse>

    @GET("api/database/articles/{id}")
    fun getDetail(
        @Path("id") id: Int
    ): Call<ArticleByIdResponse>
}