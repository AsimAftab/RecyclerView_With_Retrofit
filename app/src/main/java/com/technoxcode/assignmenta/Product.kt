package com.technoxcode.assignmenta
import com.google.gson.annotations.SerializedName
data class Product(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
