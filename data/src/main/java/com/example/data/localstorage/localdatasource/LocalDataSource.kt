package com.example.data.localstorage.localdatasource

import com.example.data.localstorage.model.PostEntity
import com.example.data.localstorage.postdao.PostDAO
import com.example.data.remotedatasource.model.PostDto

class LocalDataSource(
    private val postDao: PostDAO
) {

    suspend fun getPostsFromPage(page: Int): List<PostEntity>{
        val firstPage = postDao.getPostsFromPage(page)
        val secondPage = postDao.getPostsFromPage(page + 1)
        val finalList = firstPage + secondPage
        return finalList
    }

    suspend fun savePosts(postsList: List<PostEntity>){
        postDao.insertPosts(postsList)
    }

}