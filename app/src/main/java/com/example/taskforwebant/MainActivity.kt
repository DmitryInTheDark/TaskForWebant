package com.example.taskforwebant

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.data.localstorage.localdatasource.LocalDataSource
import com.example.data.postrepository.PostRepository
import com.example.data.remotedatasource.remotedatastorage.RemoteDataStorage
import com.example.data.remotedatasource.remotedatastorage.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit private var postRepository: PostRepository
    lateinit private var localDataSource: LocalDataSource
    lateinit private var remoteDataStorage: RemoteDataStorage




    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var db = (applicationContext as MyApp).db
        val postDao = db.getPostDAO()

        remoteDataStorage = RemoteDataStorage(RetrofitBuilder().api)
        localDataSource = LocalDataSource(postDao)

        postRepository = PostRepository(remoteDataStorage, localDataSource)


    }
}