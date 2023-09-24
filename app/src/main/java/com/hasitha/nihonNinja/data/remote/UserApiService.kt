package com.hasitha.nihonNinja.data.remote

import com.hasitha.nihonNinja.model.api.LoginRequest
import com.hasitha.nihonNinja.model.api.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse
}
