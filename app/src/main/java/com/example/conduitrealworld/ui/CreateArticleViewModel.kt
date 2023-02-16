package com.example.conduitrealworld.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.modules.Response.ArticleResponse
import com.example.conduitrealworld.modules.Response.ArticlesResponse
import com.example.conduitrealworld.modules.entites.ArticleData
import com.example.conduitrealworld.repository.ConduitApiRepository
import kotlinx.coroutines.launch

class CreateArticleViewModel(private val conduitApiRepository: ConduitApiRepository):ViewModel() {


    val artciclelivedata :LiveData<NetworkResult<ArticleResponse>>
    get() =  conduitApiRepository.articlelivedata

    fun createarticle(articleData: ArticleData){
         viewModelScope.launch {
             conduitApiRepository.createarticle(articleData)
         }
    }

}