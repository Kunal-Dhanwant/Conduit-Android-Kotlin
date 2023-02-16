package com.example.conduitrealworld.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduitrealworld.modules.entites.Article
import com.example.conduitrealworld.repository.ConduitApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyfeedViewModel(private  val conduitApiRepository: ConduitApiRepository) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO){
            conduitApiRepository.fetch_myfeed()
        }
    }
    val feed :LiveData<List<Article>>
        get() = conduitApiRepository.feed

}