package com.hasitha.nihonNinja.data.remote

import com.hasitha.nihonNinja.model.api.LoginRequest
import com.hasitha.nihonNinja.model.api.LoginResponse
import com.hasitha.nihonNinja.model.api.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("/user/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/user/signup")
    suspend fun userSignUp(@Body signUpRequest: SignUpRequest): SignUpRequest


}
