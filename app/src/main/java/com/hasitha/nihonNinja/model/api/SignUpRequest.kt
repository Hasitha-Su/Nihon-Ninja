package com.hasitha.nihonNinja.model.api

data class SignUpRequest(
    val username: String?,
    val email: String?,
    val password: UserResponse?
)