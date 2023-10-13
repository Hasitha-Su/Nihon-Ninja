package com.hasitha.nihonNinja.model.api

data class SignUpRequest(
    val name: String?,
    val email: String?,
    val password: String?
)