package com.example.conduitrealworld.ui.globe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conduitrealworld.repository.ConduitApiRepository

class GlobeViewModelFactory(private  val conduitApiRepository: ConduitApiRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GlobeViewModel(conduitApiRepository) as T
    }
}