package com.hasitha.nihonNinja.model.api

import com.hasitha.nihonNinja.model.entities.UserEntity

data class SignUpResponse(
    val statusCode: Int,
    val status: String,
    val message: String,
    val data: UserEntity
)