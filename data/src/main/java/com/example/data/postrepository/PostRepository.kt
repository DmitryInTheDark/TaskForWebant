package com.example.data.postrepository

import com.example.data.localstorage.localdatasource.LocalDataSource
import com.example.data.map.toEntity
import com.example.data.map.topPostForUser
import com.example.data.remotedatasource.remotedatastorage.RemoteDataStorage
import com.example.data.remotedatasource.model.ApiResponse
import com.example.data.remotedatasource.model.PostDto
import com.example.domain.models.PostForUser
import java.io.IOException

 private const val PAGE_SIZE = 20

class PostRepository(
    private val remoteDataStorage: RemoteDataStorage,
    private val localDataSource: LocalDataSource
) {
    suspend fun getPostsFromPage(page: Int): List<PostForUser>{
        try {
            val firstList = remoteDataStorage.getPostsForUser(page)
            val secondList = remoteDataStorage.getPostsForUser(page + 1)
            val finalList = firstList + secondList
            val entities = finalList.map {
                it.toEntity(page)
            }
            localDataSource.savePosts(entities)
            val listToUser = entities.map {
                it.topPostForUser()
            }
            return listToUser
        }catch(e: IOException){
            val firstList = localDataSource.getPostsFromPage(page)
            val secondList = localDataSource.getPostsFromPage(page + 1)
            val finalList = firstList + secondList
            return finalList.map { it.topPostForUser() }
        }
    }
}