package com.example.data.postrepository

import com.example.data.remotedatasource.apiresponseimplementation.ApiResponseImplementation
import com.example.data.remotedatasource.model.ApiResponse

class PostRepository(
    private val apiRepositoryImplementation: ApiResponseImplementation
) {

    suspend fun getApiResponce(): ApiResponse{
        return apiRepositoryImplementation.getApiResponce()
    }

}