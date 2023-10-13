package com.hasitha.nihonNinja.model.api

import com.hasitha.nihonNinja.model.entities.UserEntity

/**
 * Data class representing a signup response.
 *
 * @property statusCode The HTTP status code of the response.
 * @property status The status of the response (e.g., "success" or "error").
 * @property message A message describing the result of the signup operation.
 * @property data The user entity containing user information if the signup was successful.
 */
data class SignUpResponse(
    val statusCode: Int,
    val status: String,
    val message: String,
    val data: UserEntity
)