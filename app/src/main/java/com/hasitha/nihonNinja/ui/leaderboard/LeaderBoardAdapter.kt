package com.hasitha.nihonNinja.ui.leaderboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hasitha.nihonNinja.databinding.FragmentLeaderBoardBinding
import com.hasitha.nihonNinja.databinding.LeaderBoardItemBinding
import com.hasitha.nihonNinja.model.api.LeaderBoardUserResponse
import dagger.hilt.android.lifecycle.HiltViewModel

class LeaderBoardAdapter : RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>() {
    private var items = listOf<LeaderBoardUserResponse>()

    fun setItems(items: List<LeaderBoardUserResponse>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LeaderBoardItemBinding .inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: LeaderBoardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LeaderBoardUserResponse) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}
