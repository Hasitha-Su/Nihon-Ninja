package com.hasitha.nihonNinja.repository

import android.util.Log
import com.hasitha.nihonNinja.data.local.UserDao
import com.hasitha.nihonNinja.data.remote.UserApiService
import com.hasitha.nihonNinja.model.api.LoginRequest
import com.hasitha.nihonNinja.model.api.LoginResponse
import com.hasitha.nihonNinja.model.api.UserResponse
import com.hasitha.nihonNinja.model.entities.UserEntity
import com.hasitha.nihonNinja.util.SharedPrefManager
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userApiService: UserApiService,
    private val sharedPrefManager: SharedPrefManager
) {

    suspend fun loginUser(email: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(email, password)
        Log.d("+++ loginRequest", loginRequest.toString())

        val loginResponse = userApiService.loginUser(loginRequest)
        println("+++ Login Response: $loginResponse")

        // Assuming loginResponse contains a User object
//        userDao.saveUser(loginResponse.user)
        loginResponse.user?.let { user ->
            val userEntity = convertToEntity(user)
            userDao.saveUser(userEntity)

            // Save user ID to SharedPreferences
            sharedPrefManager.saveUserId(user.id)
        }

        return loginResponse;
//        return userApiService.loginUser(loginRequest)
    }

    private fun convertToEntity(userResponse: UserResponse): UserEntity {
        return UserEntity(
            id = userResponse.id, // Adjust this to the actual id field in UserResponse
            username = userResponse.username, // Adjust this to the actual username field in UserResponse
            email = userResponse.email // Adjust this to the actual email field in UserResponse
            // Map other fields as necessary
        )
    }

}
