package com.example.arunaapp.data.retrofit

import com.example.arunaapp.data.response.ScanResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ServiceApi {
    @Multipart
    @POST("predict")
    suspend fun postImage(
        @Part file: MultipartBody.Part
    ): ScanResponse
}