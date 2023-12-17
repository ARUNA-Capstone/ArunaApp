package com.example.arunaapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arunaapp.data.response.ArticleResponse
import com.example.arunaapp.data.response.DataItem
import com.example.arunaapp.data.retrofit.ConfigApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainVM : ViewModel() {
    private val _dataItem = MutableLiveData<List<DataItem>>()
    val dataItem: LiveData<List<DataItem>> = _dataItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        searchArticle("")
    }

    fun searchArticle(q: String) {
        _isLoading.value = true
        val client = ConfigApi.getApiService().searchArticles(q)
        client.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _dataItem.value = responseBody.data
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
