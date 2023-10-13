package com.hasitha.nihonNinja.repository

import com.hasitha.nihonNinja.data.local.UserDao
import com.hasitha.nihonNinja.data.remote.UserApiService
import com.hasitha.nihonNinja.model.api.LoginRequest
import com.hasitha.nihonNinja.model.api.LoginResponse
import com.hasitha.nihonNinja.model.api.SignUpRequest
import com.hasitha.nihonNinja.model.api.SignUpResponse
import com.hasitha.nihonNinja.util.SharedPrefManager
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userApiService: UserApiService,
    private val sharedPrefManager: SharedPrefManager
) {
    suspend fun loginUser(email: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(email, password)
//        Log.d("+++ loginRequest", loginRequest.toString())
        val loginResponse = userApiService.loginUser(loginRequest)
        loginResponse.user?.let { user ->
            userDao.saveUser(user)

            // Save user ID to SharedPreferences
            sharedPrefManager.saveUserId(user.id)
            sharedPrefManager.saveUserName(user.name)
        }
        return loginResponse;
    }

    suspend fun signUp(request: SignUpRequest): SignUpResponse {
        val signUpResponse = userApiService.userSignUp(request)

        signUpResponse.data.let { user ->
            userDao.saveUser(user)
            // Save user ID to SharedPreferences
            sharedPrefManager.saveUserId(user.id)
            sharedPrefManager.saveUserName(user.name)
        }
        return signUpResponse
    }
}
