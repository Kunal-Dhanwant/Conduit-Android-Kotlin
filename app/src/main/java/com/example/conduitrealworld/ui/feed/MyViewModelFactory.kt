package com.example.conduitrealworld.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conduitrealworld.repository.ConduitApiRepository
import com.example.conduitrealworld.ui.globe.GlobeViewModel

class MyViewModelFactory(private  val conduitApiRepository: ConduitApiRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyfeedViewModel(conduitApiRepository) as T
    }
}