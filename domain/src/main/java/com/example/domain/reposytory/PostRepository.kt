package com.example.domain.reposytory

import com.example.domain.models.PostForUser

interface PostRepository {

    fun getNewPosts(page: Int): List<PostForUser>

}