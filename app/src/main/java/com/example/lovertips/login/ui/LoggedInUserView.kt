package com.example.lovertips.login.ui

import org.json.JSONObject

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val userObject: JSONObject,
    val _token : String,
    val id :String = "",
    val firstName :String = "",
    val lastName :String  = "",
    val userName :String = "",
    val email :String = "",
    val phone :String = "",
    val gender :String = "",
    val dob :String = "",
    // val country : Array<Any>,
    val profileImages : Int = 0,
    val onlineStatus :Boolean = true,
    val createdOn: String  = "",
    val prettCreatedOn:String = "")
