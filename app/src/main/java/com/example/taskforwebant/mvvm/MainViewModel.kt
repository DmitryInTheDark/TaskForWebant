package com.example.taskforwebant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.PostForUser
import com.example.domain.reposytory.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PostRepository
): ViewModel() {

    private val _postForUser = MutableLiveData<List<PostForUser>>()
    val postForUser = _postForUser


    init {
        loadNewPosts(1)
    }



    fun loadNewPosts(page: Int){
        viewModelScope.launch {
            postForUser.postValue(repository.getNewPosts(page))
        }
    }

}