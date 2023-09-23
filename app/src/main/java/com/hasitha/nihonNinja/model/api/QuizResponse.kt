package com.hasitha.nihonNinja.model.api

data class QuizResponse(
    val quizId: Int,
    val quizName: String,
    val totalQuestions: Int,
    val questions: List<QuestionResponse>
)
