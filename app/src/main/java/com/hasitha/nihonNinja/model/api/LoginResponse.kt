package com.hasitha.nihonNinja.model.api

data class LoginResponse(
    val token: String?,
    val message: String?,
    val user: UserResponse?,
    val error: String?
)