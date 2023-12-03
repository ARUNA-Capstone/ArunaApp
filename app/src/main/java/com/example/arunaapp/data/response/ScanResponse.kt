package com.example.arunaapp.data.response
import com.google.gson.annotations.SerializedName

class ScanResponse (
    @SerializedName("contact") val contact: Contact,
    @SerializedName("information") val information: Information
)

data class Contact(
    val phone: String? = null,
    val name: String? = null,
    val contactLink: String? = null
)

data class Information(
    val image: String? = null,
    val name: String? = null,
    val description: String? = null
)