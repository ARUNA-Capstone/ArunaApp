package com.example.arunaapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.arunaapp.data.Results
import com.example.arunaapp.data.response.ScanResponse
import com.example.arunaapp.data.retrofit.ServiceApi
import okhttp3.MultipartBody

class CameraRepository (
    private val apiService: ServiceApi
){
    fun postImage(
        file: MultipartBody.Part
    ): LiveData<Results<ScanResponse>> = liveData {
        emit(Results.Loading)
        try {
            val response = apiService.postImage(file)
            emit(Results.Success(response))
        } catch (e: Exception) {
            Log.d("post_image", e.message.toString())
            emit(Results.Error(e.message.toString()))
        }
    }
}