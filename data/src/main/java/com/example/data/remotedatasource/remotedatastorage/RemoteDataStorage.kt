package com.example.data.remotedatasource.remotedatastorage

import com.example.data.remotedatasource.apiresponseinterface.ApiResponseInterface
import com.example.data.remotedatasource.model.ApiResponse
import com.example.data.remotedatasource.model.PostDto

class RemoteDataStorage(private val api: ApiResponseInterface = RetrofitBuilder().api) {

    suspend fun getPostsForUser(page: Int): List<PostDto>{
        val firstList = api.getPostsFromPage(page).data
        val secondList = api.getPostsFromPage(page + 1).data
        val finalList = firstList + secondList
        return finalList
    }

    suspend fun getApiResponce(): ApiResponse{
        return api.getPosts()
    }

}