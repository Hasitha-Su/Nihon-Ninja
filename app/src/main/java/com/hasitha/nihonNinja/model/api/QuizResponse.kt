package com.hasitha.nihonNinja.model.api

/**
 * Data class representing a response for a quiz.
 *
 * @property quizId The unique identifier of the quiz.
 * @property quizName The name or title of the quiz.
 * @property totalQuestions The total number of questions in the quiz.
 * @property questions A list of [QuestionResponse] objects representing the quiz questions.
 */
data class QuizResponse(
    val quizId: Int,
    val quizName: String,
    val totalQuestions: Int,
    val questions: List<QuestionResponse>
)
