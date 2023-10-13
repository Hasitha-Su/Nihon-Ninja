package com.hasitha.nihonNinja.model.api

/**
 * Data class representing a login request.
 *
 * @property email The email address of the user.
 * @property password The password associated with the user's account.
 */
data class LoginRequest(
    val email: String,
    val password: String
)
