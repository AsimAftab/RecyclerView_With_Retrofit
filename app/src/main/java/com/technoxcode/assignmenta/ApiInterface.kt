package com.technoxcode.assignmenta

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getProducts(): Call<ProductResponse>
}