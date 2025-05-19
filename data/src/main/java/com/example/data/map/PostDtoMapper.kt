package com.example.data.map

import com.example.data.localstorage.model.PostEntity
import com.example.data.remotedatasource.model.PostDto

fun PostDto.toEntity(page: Int) = PostEntity(id, userID, title, body, page)