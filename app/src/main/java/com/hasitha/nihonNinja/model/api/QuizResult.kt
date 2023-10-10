package com.hasitha.nihonNinja.model.api

data class QuizResult(
    val userId: Long,
    val quizId: Int,
    val score: Int
)