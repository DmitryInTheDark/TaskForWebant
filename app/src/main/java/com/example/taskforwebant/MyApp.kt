package com.example.taskforwebant

import android.app.Application
import com.example.data.localstorage.localdatasource.PostDatabase
import com.example.data.remotedatasource.remotedatastorage.RetrofitBuilder

class MyApp: Application() {

    lateinit var db: PostDatabase

    override fun onCreate() {
        super.onCreate()
        db = PostDatabase.getConnection(this)
    }
}