package com.example.conduitrealworld.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conduitrealworld.repository.ConduitApiRepository

class CreateArticleViewModelFactory(private  val conduitApiRepository: ConduitApiRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateArticleViewModel(conduitApiRepository) as T
    }
}