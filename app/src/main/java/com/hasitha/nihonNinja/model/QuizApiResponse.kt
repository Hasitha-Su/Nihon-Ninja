package com.hasitha.nihonNinja.model

data class QuizApiResponse(
    val id: Int,
    val questions: List<QuestionResponse>,
)