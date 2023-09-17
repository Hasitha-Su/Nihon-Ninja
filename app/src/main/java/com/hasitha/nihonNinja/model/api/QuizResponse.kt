package com.hasitha.nihonNinja.model.api

data class QuizResponse(
    val quizId: Int,
    val quizName: String,
    val questions: List<QuestionResponse>
)
