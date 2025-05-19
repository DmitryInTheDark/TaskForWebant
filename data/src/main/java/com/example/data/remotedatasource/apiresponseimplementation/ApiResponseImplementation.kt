package com.example.data.remotedatasource.apiresponseimplementation

import com.example.data.remotedatasource.apiresponseinterface.ApiResponseInterface
import com.example.data.remotedatasource.model.ApiResponse
import com.example.data.remotedatasource.model.Post

class ApiResponseImplementation(private val api: ApiResponseInterface = RetrofitBuilder().api) {
    suspend fun getAllPosts(page: Int): List<Post>{
        val firstList = api.getPostsFromPage(page).data
        val secondList = api.getPostsFromPage(page + 1).data
        val finalList = firstList + secondList
        return finalList
    }
}