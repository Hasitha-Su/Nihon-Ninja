package com.hasitha.nihonNinja.ui.userSignUp

import android.opengl.ETC1.isValid
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.FragmentUserSignUpBinding
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

        // Name validation
        binding.name.editText?.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.name.error = "Name is required"
            } else {
                binding.name.error = null
            }
        }

        // Email validation
        binding.email.editText?.doOnTextChanged { text, _, _, _ ->
            if (text?.isEmpty() == true) {
                binding.email.error = "Email is required."
            } else if (!isValidEmail(text.toString())) {
                binding.email.error = "Invalid email format."
            } else {
                binding.email.error = null // Clear the error if email is valid
            }
        }

        // Password validation
        binding.password.editText?.doOnTextChanged { text, _, _, _ ->
            val password = text.toString()
            if (password.isEmpty()) {
                binding.password.error = "Password is required."
            } else if (!isValidPassword(password)) {
                binding.password.error = "Password must have at least 8 characters, 1 special character, 1 number, 1 uppercase, and 1 lowercase letter."
            } else {
                binding.password.error = null // Clear the error if password is valid
            }
        }

        // Confirm Password validation
        binding.confPassword.editText?.doOnTextChanged { text, _, _, _ ->
            if (text.toString() != binding.password.editText?.text.toString()) {
                binding.confPassword.error = "Passwords do not match"
            } else {
                binding.confPassword.error = null
            }
        }

// Handle Signup click
//        binding.loginButton.setOnClickListener {
//            if (isValid()) {
//                // Do your signup logic here
//            } else {
//                Toast.makeText(context, "Please fix the errors before signing up", Toast.LENGTH_SHORT).show()
//            }
//        }


//        fun isValid(): Boolean {
//            return binding.name.error.isNullOrEmpty() &&
//                    binding.email.error.isNullOrEmpty() &&
//                    binding.password.error.isNullOrEmpty() &&
//                    binding.confPassword.error.isNullOrEmpty()
//        }

//        binding.email.editText?.doOnTextChanged { text, _, _, _ ->
//            if (text?.isEmpty() == true) {
//                binding.email.error = "Email is required."
//            } else if (!isValidEmail(text.toString())) {
//                binding.email.error = "Invalid email format."
//            } else {
//                binding.email.error = null // Clear the error if email is valid
//            }
//        }

//        binding.password.editText?.doOnTextChanged { text, _, _, _ ->
//            val password = text.toString()
//            if (password.isEmpty()) {
//                binding.password.error = "Password is required."
//            } else if (!isValidPassword(password)) {
//                binding.password.error = "Password must have at least 8 characters, 1 special character, 1 number, 1 uppercase, and 1 lowercase letter."
//            } else {
//                binding.password.error = null // Clear the error if password is valid
//            }
//        }

//        binding.loginButton.setOnClickListener {
//            val hasEmailError = binding.email.error != null
//            val hasPasswordError = binding.password.error != null
//
//            if (!hasEmailError && !hasPasswordError) {
//                val email = binding.email.editText?.text.toString().trim()
//                val password = binding.password.editText?.text.toString().trim()
////                viewModel.loginUser(email, password)
//            }
//        }

//        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
//            // Handle the login response
//            if (response.error == null && response.user != null) {
//                // Successful login
//                Log.d("+++ Successful login", response.toString())
////                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
//            } else {
//                // Error during login
//                Log.d("+++ Error during login", response.toString())
//                Toast.makeText(context, "Error, Please try again...!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_user_sign_up, container, false)
//    }
//
////    override fun onActivityCreated(savedInstanceState: Bundle?) {
////        super.onActivityCreated(savedInstanceState)
////        viewModel = ViewModelProvider(this).get(UserSignUpViewModel::class.java)
////        // TODO: Use the ViewModel
////    }

}