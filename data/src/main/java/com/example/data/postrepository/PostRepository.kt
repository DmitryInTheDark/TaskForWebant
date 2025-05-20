package com.example.data.postrepository

import android.util.Log
import com.example.data.localstorage.localdatasource.LocalDataSource
import com.example.data.map.toEntity
import com.example.data.map.toPostForUser
import com.example.data.remotedatasource.remotedatastorage.RemoteDataStorage
import com.example.domain.models.PostForUser
import com.example.domain.reposytory.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class PostRepository(
    private val remoteDataStorage: RemoteDataStorage,
    private val localDataSource: LocalDataSource
): PostRepository {

    suspend fun getPostsFromPage(page: Int): List<PostForUser>{
        try {
            val firstList = remoteDataStorage.getPostsForUser(page)
            val finalList = firstList
            val entities = finalList.map {
                it.toEntity(page)
            }
            localDataSource.savePosts(entities)
            val listToUser = entities.map {
                it.toPostForUser()
            }
            return listToUser
        }catch(e: IOException){
            val firstList = localDataSource.getPostsFromPage(page)
            val finalList = firstList
            return finalList.map { it.toPostForUser() }
        }
    }

    override suspend fun getNewPosts(page: Int): List<PostForUser> {
        var postListForUser = getPostsFromPage(page)
        return postListForUser
    }
}