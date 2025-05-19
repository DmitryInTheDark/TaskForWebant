package com.example.data.localstorage.postdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.localstorage.model.PostEntity

@Dao
interface PostDAO {

    @Query("SELECT * FROM posts WHERE page = :page")
    suspend fun getPostsFromPage(page: Int): List<PostEntity>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

}