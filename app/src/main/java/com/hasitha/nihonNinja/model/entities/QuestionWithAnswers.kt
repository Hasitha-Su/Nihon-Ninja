package com.hasitha.nihonNinja.model.entities

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing a question and its associated answers.
 *
 * @param question The [QuestionEntity] representing the question.
 * @param answers A list of [AnswerEntity] objects representing the answers to the question.
 */
data class QuestionWithAnswers(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "questionId"
    )
    val answers: List<AnswerEntity>
)