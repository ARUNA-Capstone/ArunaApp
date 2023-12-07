package com.example.arunaapp.data.response

data class Contact(
    val id: Int,
    val name: String,
    val phone: String,
    val contact_link: String
)

data class Information(
    val id: Int,
    val name: String,
    val image: String,
    val description: String
)

data class Data(
    val class_name: String,
    val confidence_score: Double,
    val contact: Contact,
    val information: Information
)

data class Status(
    val code: Int,
    val message: String
)

data class ScanResponse(
    val data: Data,
    val status: Status
)