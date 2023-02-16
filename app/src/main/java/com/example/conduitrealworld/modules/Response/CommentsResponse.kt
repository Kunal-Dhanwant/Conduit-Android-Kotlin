package com.example.conduitrealworld.modules.Response

import com.example.conduitrealworld.modules.entites.Comment

data class CommentsResponse(
    val comments: List<Comment>
)