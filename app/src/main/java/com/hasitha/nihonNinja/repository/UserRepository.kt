package com.hasitha.nihonNinja.repository

import com.hasitha.nihonNinja.data.remote.UserApiService
import com.hasitha.nihonNinja.model.api.LoginRequest
import com.hasitha.nihonNinja.model.api.LoginResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
//    private val userDao: UserDao,
    private val userApiService: UserApiService

) {

    suspend fun loginUser(email: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(email, password)
        return userApiService.loginUser(loginRequest)
    }
}
