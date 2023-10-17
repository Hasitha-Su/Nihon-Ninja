package com.hasitha.nihonNinja.ui.userSignUp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.FragmentUserSignUpBinding
import com.hasitha.nihonNinja.model.api.SignUpRequest
import com.hasitha.nihonNinja.util.Common.Companion.isValidEmail
import com.hasitha.nihonNinja.util.Common.Companion.isValidPassword
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSignUpFragment : Fragment() {

    private lateinit var viewModel: UserSignUpViewModel
    private var _binding: FragmentUserSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserSignUpViewModel::class.java)

        fun validateName(): Boolean {
            val name = binding.name.editText?.text.toString()
            if (name.isEmpty()) {
                binding.name.error = "Name is required"
                return false
            }
            binding.name.error = null
            return true
        }

        fun validateEmail(): Boolean {
            val email = binding.email.editText?.text.toString()
            if (email.isEmpty()) {
                binding.email.error = "Email is required."
                return false
            } else if (!isValidEmail(email)) {
                binding.email.error = "Invalid email format."
                return false
            }
            binding.email.error = null
            return true
        }

        fun validatePassword(): Boolean {
            val password = binding.password.editText?.text.toString()
            if (password.isEmpty()) {
                binding.password.error = "Password is required."
                return false
            } else if (!isValidPassword(password)) {
                binding.password.error = "Password must have at least 8 characters, 1 special character, 1 number, 1 uppercase, and 1 lowercase letter."
                return false
            }
            binding.password.error = null
            return true
        }

        fun validateConfPassword(): Boolean {
            if (binding.confPassword.editText?.text.toString() != binding.password.editText?.text.toString()) {
                binding.confPassword.error = "Passwords do not match"
                return false
            }
            binding.confPassword.error = null
            return true
        }

        fun isValid(): Boolean {
            val isNameValid = validateName()
            val isEmailValid = validateEmail()
            val isPasswordValid = validatePassword()
            val isConfPasswordValid = validateConfPassword()

            return isNameValid && isEmailValid && isPasswordValid && isConfPasswordValid
        }

        binding.name.editText?.doOnTextChanged { _, _, _, _ -> validateName() }
        binding.email.editText?.doOnTextChanged { _, _, _, _ -> validateEmail() }
        binding.password.editText?.doOnTextChanged { _, _, _, _ -> validatePassword() }
        binding.confPassword.editText?.doOnTextChanged { _, _, _, _ -> validateConfPassword() }

        // Handle Signup click:
        binding.loginButton.setOnClickListener {
            if (isValid()) {
                val name = binding.name.editText?.text.toString()
                val email = binding.email.editText?.text.toString()
                val password = binding.password.editText?.text.toString()

                val signUpRequest = SignUpRequest(name, email, password)
                viewModel.signUp(signUpRequest)

                viewModel.signUpResponse.observe(viewLifecycleOwner) { response ->
                    if (response != null) {
                        Toast.makeText(context, "Signup Successful!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_userSignUpFragment_to_dashboardFragment)
                    }
                }

                viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
                    if (!errorMsg.isNullOrEmpty()) {
                        Toast.makeText(context, "Signup Failed: $errorMsg", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signinText.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }
}