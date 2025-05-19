package com.example.data.remotedatasource.apiresponseimplementation

import com.example.data.remotedatasource.apiresponseinterface.ApiResponseInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://gorest.co.in/public-api/"

class RetrofitBuilder() {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiResponseInterface::class.java)
}