package com.hasitha.nihonNinja.ui.userSignUp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hasitha.nihonNinja.R

class UserSignUpFragment : Fragment() {

    companion object {
        fun newInstance() = UserSignUpFragment()
    }

    private lateinit var viewModel: UserSignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserSignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

}