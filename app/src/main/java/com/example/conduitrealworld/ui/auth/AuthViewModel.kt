package com.example.conduitrealworld.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.modules.Response.UserResponse
import com.example.conduitrealworld.modules.entites.LoginData
import com.example.conduitrealworld.modules.entites.SignupData
import com.example.conduitrealworld.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private  val userRepository: UserRepository) : ViewModel() {


   val userResponseLivedata :LiveData<NetworkResult<UserResponse>>
   get() = userRepository.userlivedata

    fun  signupuser(signupData: SignupData){
        viewModelScope.launch {
            userRepository.signupuser(signupData)
        }
    }
    fun loginuser(loginData: LoginData){
        viewModelScope.launch {
            userRepository.loginuser(loginData)
        }
    }

    fun getcurrentuser(){
        viewModelScope.launch {
            userRepository.getcurrentuser()
        }
    }


}