package com.hasitha.nihonNinja.model.entities

/**
 * Data class representing the result of a quiz question.
 *
 * @property questionNumber The number or order of the question.
 * @property isCorrect A boolean indicating whether the answer to the question was correct.
 */
data class QuestionResult(
    val questionNumber: Int,
    val isCorrect: Boolean
)
