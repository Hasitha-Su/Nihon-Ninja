package com.hasitha.nihonNinja.ui.userLogin
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import com.hasitha.nihonNinja.util.Common.Companion.isValidEmail
import com.hasitha.nihonNinja.util.Common.Companion.isValidPassword

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.email.editText?.doOnTextChanged { text, _, _, _ ->
            if (text?.isEmpty() == true) {
                binding.email.error = "Email is required."
            } else if (!isValidEmail(text.toString())) {
                binding.email.error = "Invalid email format."
            } else {
                binding.email.error = null
            }
        }

        binding.password.editText?.doOnTextChanged { text, _, _, _ ->
            val password = text.toString()
            if (password.isEmpty()) {
                binding.password.error = "Password is required."
            } else if (!isValidPassword(password)) {
                binding.password.error = "Password must have at least 8 characters, 1 special character, 1 number, 1 uppercase, and 1 lowercase letter."
            } else {
                binding.password.error = null
            }
        }

        binding.loginButton.setOnClickListener {
            val hasEmailError = binding.email.error != null
            val hasPasswordError = binding.password.error != null

            if (!hasEmailError && !hasPasswordError) {
                val email = binding.email.editText?.text.toString().trim()
                val password = binding.password.editText?.text.toString().trim()
                viewModel.loginUser(email, password)
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
            // Handle the login response
            if (response.error == null && response.user != null) {
                // Successful login
//                Log.d("+++ Successful login", response.toString())
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            } else {
                // Error during login
//                Log.d("+++ Error during login", response.toString())
                Toast.makeText(context, "Error, Please try again...!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}
