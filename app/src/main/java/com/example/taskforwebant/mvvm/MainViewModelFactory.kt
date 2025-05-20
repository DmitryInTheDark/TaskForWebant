package com.example.taskforwebant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.reposytory.PostRepository
import com.example.domain.usecases.GetPostUseCase

class MainViewModelFactory (
    private val getPostUseCase: GetPostUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getPostUseCase) as T
    }
}