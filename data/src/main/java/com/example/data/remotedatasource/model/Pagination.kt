package com.example.data.remotedatasource.model

data class Pagination(
    val total: Int,
    val pages: Int,
    val page: Int,
    val limit: Int
)
