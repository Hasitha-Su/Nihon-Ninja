package com.hasitha.nihonNinja.ui.leaderboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hasitha.nihonNinja.databinding.FragmentLeaderBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaderBoardFragment : Fragment() {

    private var _binding: FragmentLeaderBoardBinding? = null
    private lateinit var adapter: LeaderBoardAdapter

    private val binding get() = _binding!!
    private lateinit var viewModel: LeaderBoardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLeaderBoardBinding.inflate(inflater, container, false) // Initialize _binding first.
        adapter = LeaderBoardAdapter()
        binding.recyclerView.adapter = adapter // Now, binding can be accessed.

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(LeaderBoardViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.leaderBoardItems.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
