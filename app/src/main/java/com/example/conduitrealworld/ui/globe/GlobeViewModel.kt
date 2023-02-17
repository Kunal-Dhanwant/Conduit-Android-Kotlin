package com.example.conduitrealworld.ui.globe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduitrealworld.modules.Response.ArticleResponse
import com.example.conduitrealworld.modules.entites.Article
import com.example.conduitrealworld.repository.ConduitApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GlobeViewModel(private  val conduitApiRepository: ConduitApiRepository) : ViewModel() {


    fun fetchglobearticle(){
        viewModelScope.launch {
           conduitApiRepository.fetch_global_feed_articles()
        }
    }

    fun fetchglobalarticle_afterlogin(){
        viewModelScope.launch{
            conduitApiRepository.fetchglobalarticle_afterlogin()
        }
    }
    val article:LiveData<List<Article>>
        get() = conduitApiRepository.articles



    fun favouritearticle(slug :String){
        viewModelScope.launch {
            conduitApiRepository.favouritearticle(slug)
        }
    }

    fun unfavouritearticle(slug :String){
        viewModelScope.launch {
            conduitApiRepository.unfavouritearticle(slug)
        }
    }
    val articledata : LiveData<ArticleResponse>
        get() =  conduitApiRepository.articledata


}