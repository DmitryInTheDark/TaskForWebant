package com.example.data.remotedatasource.apiresponseinterface

import com.example.data.remotedatasource.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiResponseInterface{
    @GET("posts")
    suspend fun getPostsFromPage(@Query("page") page: Int): ApiResponse
}