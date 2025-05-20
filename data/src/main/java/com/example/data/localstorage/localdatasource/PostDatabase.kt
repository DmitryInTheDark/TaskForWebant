package com.example.data.localstorage.localdatasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.localstorage.model.PostEntity
import com.example.data.localstorage.postdao.PostDAO

private const val DATABASE_NAME = "Posts.db"

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase: RoomDatabase() {

    abstract fun getPostDAO(): PostDAO

    companion object{
        fun getConnection(context: Context): PostDatabase{
            return Room.databaseBuilder(
                context,
                PostDatabase::class.java,
                DATABASE_NAME
                ).build()
        }
    }
}