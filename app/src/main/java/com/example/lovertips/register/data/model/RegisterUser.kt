package com.example.lovertips.register.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class RegisterUser(
    val userId: String,
    val displayName: String
)
