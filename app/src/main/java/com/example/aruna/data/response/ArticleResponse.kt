package com.example.aruna.data.response

import com.google.gson.annotations.SerializedName

	data class ArticleResponse(

		@field:SerializedName("data")
		val data: List<DataItem>,

		@field:SerializedName("status")
		val status: Status
	)
	data class DataItem(

		@field:SerializedName("image")
		val image: String,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("description")
		val description: String,

		@field:SerializedName("id")
		val id: Int
	)

	data class Status(

		@field:SerializedName("code")
		val code: Int,

		@field:SerializedName("message")
		val message: String
	)
