package com.example.lovertips.register.ui

/**
 * Data validation state of the registry form.
 */
data class RegisterFormState(
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
