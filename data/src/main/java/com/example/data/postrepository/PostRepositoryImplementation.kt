package com.example.data.postrepository

import android.util.Log
import com.example.data.localstorage.localdatasource.LocalDataSource
import com.example.data.map.toEntity
import com.example.data.map.toPostForUser
import com.example.data.remotedatasource.remotedatastorage.RemoteDataStorage
import com.example.domain.models.PostForUser
import com.example.domain.reposytory.PostRepository
import java.io.IOException

class PostRepositoryImplementation(
    private val remoteDataStorage: RemoteDataStorage,
    private val localDataSource: LocalDataSource
): PostRepository {

    suspend fun getPostsFromPage(page: Int): List<PostForUser>{
        try {
            val finalList = remoteDataStorage.getPostsForUser(page)
            val entities = finalList.map {
                it.toEntity(page)
            }
            localDataSource.savePosts(entities)
            val listToUser = entities.map {
                it.toPostForUser()
            }
            return listToUser
        }catch(e: IOException){
            val finalList = localDataSource.getPostsFromPage(page)
            return finalList.map { it.toPostForUser() }
        }
    }

    override suspend fun getNewPosts(page: Int): List<PostForUser> {
        var postListForUser = getPostsFromPage(page)
        return postListForUser
    }
}