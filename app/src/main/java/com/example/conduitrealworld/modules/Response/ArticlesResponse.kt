package com.example.conduitrealworld.modules.Response

import com.example.conduitrealworld.modules.entites.Article

data class ArticlesResponse(
    val articles: List<Article>,
    val articlesCount: Int
)