package com.example.conduitrealworld.ui.auth

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conduitrealworld.repository.UserRepository

class AuthViewModelFactory(private  val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }

}