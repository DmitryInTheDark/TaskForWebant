package com.example.domain.reposytory

import com.example.domain.models.PostForUser

interface PostRepository {

    suspend fun getNewPosts(page: Int): List<PostForUser>

}