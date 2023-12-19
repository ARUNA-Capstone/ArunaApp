package com.example.arunaapp.injection

import android.content.Context
import com.example.arunaapp.data.repository.CameraRepository
import com.example.arunaapp.data.retrofit.ConfigApi

object Data {
    fun provideRepository(context: Context): CameraRepository {
        val apiService = ConfigApi.getApiService()
        return CameraRepository(apiService)
    }
}