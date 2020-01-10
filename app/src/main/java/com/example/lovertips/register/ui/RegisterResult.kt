package com.example.lovertips.register.ui

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: RegisterUserView? = null,
    val error: RegisterErrorView? = null

    //val error: Int? = null
)
