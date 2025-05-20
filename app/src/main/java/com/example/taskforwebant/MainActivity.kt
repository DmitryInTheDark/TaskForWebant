package com.example.taskforwebant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.localstorage.localdatasource.LocalDataSource
import com.example.data.postrepository.PostRepositoryImplementation
import com.example.data.remotedatasource.remotedatastorage.RemoteDataStorage
import com.example.data.remotedatasource.remotedatastorage.RetrofitBuilder
import com.example.domain.models.PostForUser
import com.example.taskforwebant.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit private var binding: ActivityMainBinding
    lateinit private var adapter: MainRecyclerViewAdapter
    private var pageCount = 1

    lateinit private var postRepositoryImplementation: PostRepositoryImplementation
    lateinit private var localDataSource: LocalDataSource
    lateinit private var remoteDataStorage: RemoteDataStorage

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = (applicationContext as MyApp).db
        val postDao = db.getPostDAO()

        remoteDataStorage = RemoteDataStorage(RetrofitBuilder().api)
        localDataSource = LocalDataSource(postDao)

        postRepositoryImplementation = PostRepositoryImplementation(remoteDataStorage, localDataSource)

        viewModel = ViewModelProvider(this, MainViewModelFactory(postRepositoryImplementation))[MainViewModel::class.java]

        adapter = MainRecyclerViewAdapter()

        binding.RCView.layoutManager = LinearLayoutManager(this)
        binding.RCView.adapter = adapter

        viewModel.postForUser.observe(this){
            adapter.getNewPosts(it)
            pageCount++
        }



        binding.RCView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val itemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if(lastVisibleItem >= itemCount -3){
                    viewModel.loadNewPosts(pageCount)
                    pageCount++
                }
            }
        })

    }
}