package com.hasitha.nihonNinja.model.entities

import androidx.lifecycle.LiveData

data class QuizWithQuestionsAndAnswers(
    val totalQuestions: Int,
    val questionsWithAnswers: LiveData<List<QuestionWithAnswers>>
)
