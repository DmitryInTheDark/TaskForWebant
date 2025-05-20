package com.example.taskforwebant

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.data.localstorage.localdatasource.LocalDataSource
import com.example.data.postrepository.PostRepository
import com.example.data.remotedatasource.remotedatastorage.RemoteDataStorage
import com.example.data.remotedatasource.remotedatastorage.RetrofitBuilder
import com.example.taskforwebant.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit private var binding: ActivityMainBinding

    lateinit private var postRepository: PostRepository
    lateinit private var localDataSource: LocalDataSource
    lateinit private var remoteDataStorage: RemoteDataStorage

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            Thread.sleep(2500)
            Log.i("my", "AAA")
            return@setKeepOnScreenCondition false
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = (applicationContext as MyApp).db
        val postDao = db.getPostDAO()

        remoteDataStorage = RemoteDataStorage(RetrofitBuilder().api)
        localDataSource = LocalDataSource(postDao)

        postRepository = PostRepository(remoteDataStorage, localDataSource)

        viewModel = ViewModelProvider(this, MainViewModelFactory(postRepository))[MainViewModel::class.java]

        viewModel.postForUser.observe(this){
            Log.i("my", "${it.joinToString("\n\n")}")
        }

    }
}