package com.example.data.map

import com.example.data.localstorage.model.PostEntity
import com.example.data.remotedatasource.model.PostDto
import com.example.domain.models.PostForUser

fun PostEntity.topPostForUser() = PostForUser(title, body)