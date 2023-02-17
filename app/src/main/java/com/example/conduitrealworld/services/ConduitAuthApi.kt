package com.example.conduitrealworld.services

import com.example.conduitrealworld.modules.Request.CreateArticleRequest
import com.example.conduitrealworld.modules.Response.ArticleResponse
import com.example.conduitrealworld.modules.Response.ArticlesResponse
import com.example.conduitrealworld.modules.Response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitAuthApi {

    @GET("articles/feed")
    suspend fun getfeedArticles():Response<ArticlesResponse>


    @GET("articles")
    suspend fun getAllArticlesafterlogin( @Query("limit") limit: String? = null): Response<ArticlesResponse>

    @GET("user")
    suspend fun getCurrentUser():Response<UserResponse>

    @POST("articles")
    suspend fun  createarticle(@Body createArticleRequest: CreateArticleRequest):Response<ArticleResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unfavoriteArticle(
        @Path("slug") slug: String
    ): Response<ArticleResponse>




}