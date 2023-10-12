package com.hasitha.nihonNinja.ui.userLogin

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hasitha.nihonNinja.LoginState
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
//        viewModel = by viewModels()


//        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
//            val email = view.findViewById<EditText>(R.id.email).text.toString()
//            val password = view.findViewById<EditText>(R.id.password).text.toString()
//            viewModel.loginUser(email, password)
//        }

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            viewModel.loginUser(email, password)
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
            // Handle the login response
            if (response.error == null && response.user != null) {
                // Successful login
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            } else {
                // Error during login
                Toast.makeText(context, "Error, Please try again...!", Toast.LENGTH_SHORT).show()
//                AlertDialog.Builder(context)
//                    .setTitle("Title")
//                    .setMessage("Your message here")
//                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
//                    .show()
            }
        }

//        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
//            when (response) {
//                is LoginState.Success -> {
//                    binding.progressBar.visibility = View.GONE // Hide ProgressBar on Success
//                    // Handle successful login
//                }
//                is LoginState.Error -> {
//                    binding.progressBar.visibility = View.GONE // Hide ProgressBar on Error
//                    // Handle error
//                }
//                LoginState.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE // Show ProgressBar on Loading
//                }
//            }
//        }

/*
        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is LoginState.Success -> {
                    binding.progressindicator.visibility = View.GONE // Hide ProgressBar
                    //binding.signInButton.isEnabled = true // Enable Sign In Button
                    // Handle successful login
                }
                is LoginState.Error -> {
                    binding.progressindicator.visibility = View.GONE // Hide ProgressBar
                    //binding.signInButton.isEnabled = true // Enable Sign In Button
                    // Handle error
                }
                LoginState.Loading -> {
                    binding.progressindicator.visibility = View.VISIBLE // Show ProgressBar
                    //binding.signInButton.isEnabled = false // Disable Sign In Button
                }
            }
        }
 */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}
