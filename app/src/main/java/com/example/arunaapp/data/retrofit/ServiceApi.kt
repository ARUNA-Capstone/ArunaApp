package com.example.arunaapp.data.retrofit

import com.example.arunaapp.data.response.ArticleResponse
import com.example.arunaapp.data.response.DetailArticleResponse
import com.example.arunaapp.data.response.ScanResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @Multipart
    @POST("api/predict")
    suspend fun postImage(
        @Part file: MultipartBody.Part
    ): ScanResponse
    @GET("api/search")
    fun searchArticles(
        @Query("q") id: String,
    ): Call<ArticleResponse>

    @GET("api/database/articles/{id}")
    fun getDetail(
        @Path("id") id: Int
    ): Call<DetailArticleResponse>
}