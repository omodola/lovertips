package com.example.lovertips.register.ui

import org.json.JSONObject

/**
 * User details post authentication that is exposed to the UI
 */
data class RegisterErrorView(
    val userObject: JSONObject,
    val _token : String,
    val id :String = "",
    val firstName :String = "",
    val lastName :String  = "",
    val userName :String = ""
)
