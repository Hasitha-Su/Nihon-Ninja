package com.hasitha.nihonNinja.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hasitha.nihonNinja.model.entities.QuizEntity
import com.hasitha.nihonNinja.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    quizRepository: QuizRepository

) : ViewModel() {

    val allQuizzes: LiveData<List<QuizEntity>> = quizRepository.getAllQuizzes()

}
