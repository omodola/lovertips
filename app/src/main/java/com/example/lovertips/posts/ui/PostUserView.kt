package com.example.lovertips.posts.ui

import org.json.JSONObject

data class PostUserView(
    val _token : String="",
    val id:String = "",
    val comments :String = "",
    val title :String = "",
    val body :String = "",
    val userId :String  = "",
    val user :JSONObject ,
    val userFirstName :String = "",
    val userLastName :String = "",
    val createdOn :String = "",
    val views :String = "",
    val likes : Int = 0,
    val tags :Boolean = true,
    val shares: String  = "",
    val upVotes:String = "" ,
    val downVotes :String  = "",
    val reports :String = "",
    val viewsCount :String = "",
    val likesCount :String = "",
    val sharesCount :String = "",
    val upVotesCount :String = "",
    val downVotesCount : Int = 0,
    val reportsCount :Boolean = true,
    val commentsCount: String  = ""

    )
