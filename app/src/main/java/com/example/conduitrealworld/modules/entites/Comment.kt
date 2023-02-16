package com.example.conduitrealworld.modules.entites

data class Comment(
    val author: Author,
    val body: String,
    val createdAt: String,
    val id: Int,
    val updatedAt: String
)