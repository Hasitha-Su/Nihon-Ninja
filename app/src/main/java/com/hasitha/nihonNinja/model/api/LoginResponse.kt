package com.hasitha.nihonNinja.model.api

import com.hasitha.nihonNinja.model.entities.UserEntity

/**
 * Data class representing a login response.
 *
 * @property token The authentication token if the login was successful.
 * @property message A message indicating the result of the login operation.
 * @property user The user entity containing user information if available.
 * @property error An error message if the login was not successful.
 */
data class LoginResponse(
    val token: String?,
    val message: String?,
    val user: UserEntity?,
    val error: String?
)