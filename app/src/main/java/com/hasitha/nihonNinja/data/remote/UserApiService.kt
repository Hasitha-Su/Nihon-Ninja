package com.hasitha.nihonNinja.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import com.hasitha.nihonNinja.model.api.LoginRequest
import com.hasitha.nihonNinja.model.api.LoginResponse
import com.hasitha.nihonNinja.model.api.SignUpRequest
import com.hasitha.nihonNinja.model.api.SignUpResponse

/**
 * Retrofit API service interface for user-related API requests.
 */
interface UserApiService {

    /**
     * Sends an HTTP POST request to log in a user.
     *
     * @param loginRequest The [LoginRequest] object representing the login request data.
     * @return A [LoginResponse] object representing the response to the login request.
     */
    @POST("/user/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    /**
     * Sends an HTTP POST request to sign up a user.
     *
     * @param request The [SignUpRequest] object representing the user signup request data.
     * @return A [SignUpResponse] object representing the response to the signup request.
     */
    @POST("/user/signup")
    suspend fun userSignUp(@Body request: SignUpRequest): SignUpResponse

}
