package com.example.aruna.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aruna.data.response.ArticleResponse
import com.example.aruna.data.response.DataItem
import com.example.aruna.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainViewModel: ViewModel(){
    private val _dataItem = MutableLiveData<List<DataItem>>()
    val dataItem: LiveData<List<DataItem>> = _dataItem

    companion object{
        private const val TAG = "MainViewModel"
    }

    init {
        searchArticle("")
    }

    fun searchArticle(q: String){
        val client = ApiConfig.getApiService().searchArticles(q)
        client.enqueue(object : retrofit2.Callback<ArticleResponse>{
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null){
                        _dataItem.value = responseBody.data
                    }
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}