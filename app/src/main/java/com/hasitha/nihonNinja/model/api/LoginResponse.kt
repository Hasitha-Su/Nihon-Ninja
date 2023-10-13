package com.hasitha.nihonNinja.model.api

import com.hasitha.nihonNinja.model.entities.UserEntity

data class LoginResponse(
    val token: String?,
    val message: String?,
    val user: UserEntity?,
    val error: String?
)