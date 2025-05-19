package com.example.data.localstorage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity (
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val userID: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val body: String,
    @ColumnInfo
    val page: Int
)