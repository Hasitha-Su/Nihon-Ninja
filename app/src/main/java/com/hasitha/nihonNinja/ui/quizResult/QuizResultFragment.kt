package com.hasitha.nihonNinja.ui.quizResult

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.FragmentQuizResultBinding

class QuizResultFragment : Fragment() {

    private lateinit var viewModel: QuizResultViewModel
    private lateinit var binding: FragmentQuizResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_result, container, false)
        viewModel = ViewModelProvider(this)[QuizResultViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: QuizResultFragmentArgs by navArgs()
        val param1 = args.param1
        val param2 = args.param2

        viewModel.resultText.value = "You have completed the quiz. Your score is:  $param1 / $param2"
    }
}
