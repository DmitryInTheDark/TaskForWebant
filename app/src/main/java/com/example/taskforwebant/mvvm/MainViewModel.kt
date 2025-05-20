package com.example.taskforwebant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        CoroutineScope(Dispatchers.IO).launch {
            postForUser.postValue(repository.getNewPosts(1))
        }
    }


}