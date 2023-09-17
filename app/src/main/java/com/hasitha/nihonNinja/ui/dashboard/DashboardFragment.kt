package com.hasitha.nihonNinja.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var quizViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding
    private val quizAdapter = QuizAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        quizViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding.viewModel = quizViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = quizAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        quizViewModel.allQuizzes.observe(viewLifecycleOwner) { quizzes ->
            Log.d("+++ allQuizzes 2", quizzes.toString())
            quizzes?.let { quizAdapter.submitList(it) }
        }
    }
}