package com.hasitha.nihonNinja.model

data class QuestionResponse(
    val id: Int,
    val sinSentence: String,
    val answerOrder: List<Int>,
    val japQuestion: List<JapaneseWordResponse>
)