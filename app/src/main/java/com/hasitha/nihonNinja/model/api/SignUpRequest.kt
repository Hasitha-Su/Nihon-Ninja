package com.hasitha.nihonNinja.model.api

/**
 * Data class representing a signup request.
 *
 * @property name The name of the user (optional).
 * @property email The email address of the user (optional).
 * @property password The password for the user's account (optional).
 */
data class SignUpRequest(
    val name: String?,
    val email: String?,
    val password: String?
)