package com.hasitha.nihonNinja.data.remote

import com.hasitha.nihonNinja.model.api.LoginRequest
import com.hasitha.nihonNinja.model.api.LoginResponse
import com.hasitha.nihonNinja.model.api.SignUpRequest
import com.hasitha.nihonNinja.model.api.SignUpResponse
import com.hasitha.nihonNinja.model.api.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("/user/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/user/signup")
    suspend fun userSignUp(@Body request: SignUpRequest): SignUpResponse



}
