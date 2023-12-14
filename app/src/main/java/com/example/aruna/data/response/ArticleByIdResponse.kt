package com.example.aruna.data.response

import com.google.gson.annotations.SerializedName

data class ArticleByIdResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: Status
)

data class Data(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int
)
