package com.hasitha.nihonNinja.model.api

/**
 * Data class representing a response for a quiz question.
 *
 * @property questionId The unique identifier of the question.
 * @property sentence The sentence or statement of the question.
 * @property answerOrder The order or positions of the correct answers.
 * @property answers A list of [AnswerResponse] objects representing possible answers.
 */
data class QuestionResponse(
    val questionId: Int,
    val sentence: String,
    val answerOrder: List<Int>,
    val answers: List<AnswerResponse>
)