package com.example.data.map

import com.example.data.localstorage.model.PostEntity
import com.example.domain.models.PostForUser

fun PostEntity.toPostForUser() = PostForUser(title, body)