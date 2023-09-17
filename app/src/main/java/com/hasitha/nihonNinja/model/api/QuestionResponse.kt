package com.hasitha.nihonNinja.model.api

data class QuestionResponse(
    val questionId: Int,
    val sentence: String,
    val answerOrder: List<Int>,
    val answers: List<AnswerResponse>
)