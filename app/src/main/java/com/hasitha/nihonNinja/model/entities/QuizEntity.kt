package com.hasitha.nihonNinja.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a quiz.
 *
 * @param quizId The unique identifier of the quiz.
 * @param quizName The name or title of the quiz.
 * @param totalQuestions The total number of questions in the quiz.
 */
@Entity(tableName = "quiz_table")
data class QuizEntity(
    @PrimaryKey val quizId: Int,
    val quizName: String,
    val totalQuestions: Int
)





