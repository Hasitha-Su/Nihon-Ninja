package com.hasitha.nihonNinja.model.api

/**
 * Data class representing the result of a quiz taken by a user.
 *
 * @property userId The unique identifier of the user who took the quiz.
 * @property quizId The unique identifier of the quiz.
 * @property score The score achieved by the user in the quiz.
 */
data class QuizResult(
    val userId: Long,
    val quizId: Int,
    val score: Int
)