package com.example.conduitrealworld.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.modules.Request.LoginDataRequest
import com.example.conduitrealworld.modules.Request.SignupRequest
import com.example.conduitrealworld.modules.Response.UserResponse
import com.example.conduitrealworld.modules.entites.LoginData
import com.example.conduitrealworld.modules.entites.SignupData
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.RetroFitHelper
import org.json.JSONObject
import retrofit2.Response
import kotlin.math.log

class UserRepository(private val conduitApi: ConduitApi) {

    private  val conduitAuthApi = RetroFitHelper.authApi


    private val _userlivedata = MutableLiveData<NetworkResult<UserResponse>>()
    val userlivedata : LiveData<NetworkResult<UserResponse>>
    get() = _userlivedata
    suspend fun  signupuser(signupData: SignupData){
        _userlivedata.postValue(NetworkResult.Loading())
       val response= conduitApi.signupUser(SignupRequest(signupData))
        Log.d("CONDUIT-----------",response.body().toString())
        handelResponse(response)



    }

    suspend fun loginuser(loginData: LoginData){
        _userlivedata.postValue(NetworkResult.Loading())
        val response= conduitApi.loginuser(LoginDataRequest(loginData))
        Log.d("CONDUIT-----------",response.body().toString())
        handelResponse(response)
    }



    suspend fun  getcurrentuser(){

        _userlivedata.postValue(NetworkResult.Loading())
        val response = conduitAuthApi.getCurrentUser()
        handelResponse(response)

    }





    private fun handelResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {

            _userlivedata.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {

            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userlivedata.postValue(NetworkResult.Error(message = errorObj.getString("errors")))
        } else {

            _userlivedata.postValue(NetworkResult.Error(message = "Something went wrong"))

        }
    }
}