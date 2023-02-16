package com.example.conduitrealworld.services

import com.example.conduitrealworld.modules.Request.CreateArticleRequest
import com.example.conduitrealworld.modules.Response.ArticleResponse
import com.example.conduitrealworld.modules.Response.ArticlesResponse
import com.example.conduitrealworld.modules.Response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConduitAuthApi {

    @GET("articles/feed")
    suspend fun getfeedArticles():Response<ArticlesResponse>



    @GET("user")
    suspend fun getCurrentUser():Response<UserResponse>

    @POST("articles")
    suspend fun  createarticle(@Body createArticleRequest: CreateArticleRequest):Response<ArticleResponse>

}