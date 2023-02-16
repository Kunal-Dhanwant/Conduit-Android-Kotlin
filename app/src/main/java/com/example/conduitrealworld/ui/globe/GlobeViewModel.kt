package com.example.conduitrealworld.ui.globe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduitrealworld.modules.entites.Article
import com.example.conduitrealworld.repository.ConduitApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GlobeViewModel(private  val conduitApiRepository: ConduitApiRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            conduitApiRepository.fetch_global_feed_articles()
        }
    }
    val article:LiveData<List<Article>>
        get() = conduitApiRepository.articles

}