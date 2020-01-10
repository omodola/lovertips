package com.example.lovertips.posts.data.model

data class CreatePostRequest(
    val title: String = "",
    val body: String
   // val images: String

)


data class GetAllPostsRequest(
    val title: String,
    val body: String
    // val images: String

)