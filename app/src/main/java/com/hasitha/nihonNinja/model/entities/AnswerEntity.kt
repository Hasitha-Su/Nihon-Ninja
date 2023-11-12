package com.hasitha.nihonNinja.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entity class representing an answer to a question.
 *
 * @param answerId The unique identifier of the answer (auto-generated).
 * @param questionId The unique identifier of the question to which this answer belongs.
 * @param order The order or position of the answer.
 * @param word The word representing the answer.
 */
@Entity(tableName = "answer_table",
    foreignKeys = [ForeignKey(
        entity = QuestionEntity::class,
        parentColumns = ["questionId"],
        childColumns = ["questionId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["questionId"])]
)
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true) val answerId: Int = 0,
    val questionId: Int,
    val order: Int,
    val word: String
)


