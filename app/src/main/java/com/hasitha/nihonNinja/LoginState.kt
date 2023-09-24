package com.hasitha.nihonNinja

import com.hasitha.nihonNinja.model.api.LoginResponse

sealed class LoginState {
    data class Success(val user: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
    object Loading : LoginState()
}
