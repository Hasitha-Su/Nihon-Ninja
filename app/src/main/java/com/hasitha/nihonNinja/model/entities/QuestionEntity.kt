package com.hasitha.nihonNinja.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "question_table",
    foreignKeys = [ForeignKey(
        entity = QuizEntity::class,
        parentColumns = ["quizId"],
        childColumns = ["quizId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["quizId"])]
)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val questionId: Int = 0,
    val quizId: Int,
    val sentence: String,
    val answerOrder: List<Int>
)

