package com.example.arunaapp.data.response

import com.google.gson.annotations.SerializedName

data class DetailArticleResponse(

    @field:SerializedName("data")
    val data: DataArticle,

    @field:SerializedName("status")
    val status: Status
)

data class DataArticle(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: Int
)