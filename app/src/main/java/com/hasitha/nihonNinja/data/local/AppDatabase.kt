package com.hasitha.nihonNinja.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hasitha.nihonNinja.model.entities.AnswerEntity
import com.hasitha.nihonNinja.model.entities.QuestionEntity
import com.hasitha.nihonNinja.model.entities.QuizEntity
import com.hasitha.nihonNinja.model.entities.UserEntity

@Database(
    entities = [UserEntity::class, QuizEntity::class, QuestionEntity::class, AnswerEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao
    abstract fun userDao(): UserDao
}
