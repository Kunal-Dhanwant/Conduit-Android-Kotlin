package com.example.conduitrealworld.services

import com.example.conduitrealworld.modules.Request.LoginDataRequest
import com.example.conduitrealworld.modules.Request.SignupRequest
import com.example.conduitrealworld.modules.Response.ArticlesResponse
import com.example.conduitrealworld.modules.Response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ConduitApi {



    @GET("articles")
    suspend fun getAllArticles( @Query("limit") limit: String? = null): Response<ArticlesResponse>


    @POST("users")
    suspend fun signupUser(
        @Body userCreds: SignupRequest
    ): Response<UserResponse>


    @POST("users/login")
    suspend fun loginuser(@Body loginCreds:LoginDataRequest):Response<UserResponse>


}