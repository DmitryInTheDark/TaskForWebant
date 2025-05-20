package com.example.taskforwebant

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.PostForUser
import com.example.domain.reposytory.PostRepository
import com.example.domain.usecases.GetPostUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPostUseCase: GetPostUseCase
): ViewModel() {

    private val _postForUser = MutableLiveData<List<PostForUser>>()
    val postForUser = _postForUser

    init {
        loadNewPosts(1)
    }



    fun loadNewPosts(page: Int){
        viewModelScope.launch {
            postForUser.postValue(getPostUseCase.getNewPosts(page))
        }
    }

}