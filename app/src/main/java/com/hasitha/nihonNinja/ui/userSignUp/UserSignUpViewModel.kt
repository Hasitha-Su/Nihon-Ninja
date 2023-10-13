package com.hasitha.nihonNinja.ui.userSignUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasitha.nihonNinja.model.api.SignUpRequest
import com.hasitha.nihonNinja.model.api.SignUpResponse
import com.hasitha.nihonNinja.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSignUpViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    // LiveData to hold the signup response
    private val _signUpResponse = MutableLiveData<SignUpResponse>()
    val signUpResponse: LiveData<SignUpResponse> get() = _signUpResponse

    // LiveData to hold error messages
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun signUp(request: SignUpRequest) {
        viewModelScope.launch {
            try {
                val response = userRepository.signUp(request)
                _signUpResponse.value = response
            } catch (e: Exception) {
                _error.value = "Error signing up: ${e.message}"
            }
        }
    }
}