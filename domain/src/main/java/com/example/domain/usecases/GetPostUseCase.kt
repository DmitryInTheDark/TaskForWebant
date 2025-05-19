package com.example.domain.usecases

import com.example.domain.models.PostForUser
import com.example.domain.reposytory.PostRepository

class GetPostUseCase(private val postRepository: PostRepository): PostRepository {

    override fun getNewPosts(page: Int): List<PostForUser>{
        return postRepository.getNewPosts(page)
    }

}