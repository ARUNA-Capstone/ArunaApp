package com.example.arunaapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.arunaapp.data.repository.CameraRepository
import okhttp3.MultipartBody

class CameraVM (
    private val repository: CameraRepository
) : ViewModel() {

    fun postImage(
        file: MultipartBody.Part
    ) = repository.postImage(file)
}