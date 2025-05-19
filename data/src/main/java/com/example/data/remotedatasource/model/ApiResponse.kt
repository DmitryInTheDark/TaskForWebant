package com.example.data.remotedatasource.model

data class ApiResponse(
    val code: Int,
    val meta: Meta,
    val data: List<Post>
)
