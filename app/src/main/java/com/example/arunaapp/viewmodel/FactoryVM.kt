package com.example.arunaapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arunaapp.data.repository.CameraRepository
import com.example.arunaapp.injection.Data

class FactoryVM (
    private val repository: CameraRepository
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CameraVM::class.java) -> {
                CameraVM(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: FactoryVM? = null
        fun getInstance(context: Context): FactoryVM =
            instance ?: synchronized(this) {
                instance ?: FactoryVM(Data.provideRepository(context))
            }.also {
                instance = it
            }
    }

}