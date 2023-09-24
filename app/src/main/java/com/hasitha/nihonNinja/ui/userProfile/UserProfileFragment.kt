package com.hasitha.nihonNinja.ui.userProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {

    private lateinit var viewModel: UserProfileViewModel
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
//        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        // Bind the ViewModel
//        binding.userProfileViewModel  = viewModel
        binding.lifecycleOwner = this  // Enable LiveData to automatically update the data-binding layout

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSignOut: Button = view.findViewById(R.id.sign_out_button)

        btnSignOut.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

