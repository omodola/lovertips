package com.example.lovertips.posts.ui

data class PostResult(
    val success: PostUserView? = null,
    val error: Int? = null
)

data class GetAllPostsResult(
    val success: GetPostUserView? = null,
    val error: Int? = null
)