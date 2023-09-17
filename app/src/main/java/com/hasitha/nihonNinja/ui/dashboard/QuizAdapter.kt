package com.hasitha.nihonNinja.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.databinding.ItemQuizBinding
import com.hasitha.nihonNinja.model.entities.QuizEntity

class QuizAdapter : ListAdapter<QuizEntity, QuizAdapter.QuizViewHolder>(QuizDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding: ItemQuizBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_quiz,
            parent,
            false
        )
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class QuizViewHolder(private val binding: ItemQuizBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quiz: QuizEntity) {
            binding.quizName.text = quiz.quizName
            binding.root.setOnClickListener {
                val action = DashboardFragmentDirections.actionDashboardFragmentToSinhalaToJapTranslationQuizFragment3()
                it.findNavController().navigate(action)
            }
        }
    }

    class QuizDiffCallback : DiffUtil.ItemCallback<QuizEntity>() {
        override fun areItemsTheSame(oldItem: QuizEntity, newItem: QuizEntity): Boolean {
            return oldItem.quizId == newItem.quizId
        }

        override fun areContentsTheSame(oldItem: QuizEntity, newItem: QuizEntity): Boolean {
            return oldItem == newItem
        }
    }
}



