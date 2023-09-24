package com.hasitha.nihonNinja.ui.userLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasitha.nihonNinja.model.api.LoginResponse
import com.hasitha.nihonNinja.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        try {
            val response = userRepository.loginUser(email, password)
            _loginResponse.value = response
        } catch (e: Exception) {
            _loginResponse.value = LoginResponse(null, "Error logging in:" + e.message, null, null)
        }
    }
}
