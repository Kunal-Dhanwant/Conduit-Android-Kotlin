package com.example.conduitrealworld.modules.entites

data class ArticleData(
    val body: String?,
    val description: String?,
    val tagList: List<String>?=null,
    val title: String?
)