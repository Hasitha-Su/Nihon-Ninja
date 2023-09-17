package com.hasitha.nihonNinja.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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


