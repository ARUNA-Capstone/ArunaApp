package com.example.aruna.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aruna.data.response.ArticleByIdResponse
import com.example.aruna.data.response.Data
import com.example.aruna.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val _detail = MutableLiveData<Data>()
    val detail: LiveData<Data> = _detail

    companion object{
        private const val TAG = "DetailViewModel"
    }

    fun articleDetail(id : Int){
        val client = ApiConfig.getApiService().getDetail(id)
        client.enqueue(object : Callback<ArticleByIdResponse>{
            override fun onResponse(
                call: Call<ArticleByIdResponse>,
                response: Response<ArticleByIdResponse>
            ) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        _detail.value = responseBody.data
                    }
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArticleByIdResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}