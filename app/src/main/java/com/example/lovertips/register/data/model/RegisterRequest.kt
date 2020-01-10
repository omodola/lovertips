package com.example.lovertips.register.data.model


data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword:String,
    val gender:String,
    val phoneNumber:String = ""
)
