package com.hasitha.nihonNinja.model.api

/**
 * Data class representing an answer response.
 *
 * @property order The order or position of the answer.
 * @property word The word representing the answer.
 */
data class AnswerResponse(
    val order: Int,
    val word: String
)