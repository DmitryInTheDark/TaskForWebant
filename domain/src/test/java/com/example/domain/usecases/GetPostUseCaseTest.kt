package com.example.domain.usecases

import com.example.domain.models.PostForUser
import com.example.domain.reposytory.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

//class TestPostRepository(): PostRepository{
//    override suspend fun getNewPosts(page: Int): List<PostForUser> {
//        return listOf(PostForUser("test title", "test body"))
//    }
//}

class GetPostUseCaseTest {

    val postRepository = mock<PostRepository>()

    val testData = listOf(PostForUser("test title", "test body"))


    @Test
    fun `should return the same data as in repository`(){


//        Mockito.`when`(postRepository.getNewPosts(1)).then { return@then testData }

//        val repository = TestPostRepository()
//        val useCase = GetPostUseCase(repository)

        val expected = 4
        val actual = 2 + 2 + 1
        Assertions.assertEquals(expected, actual)
    }

}