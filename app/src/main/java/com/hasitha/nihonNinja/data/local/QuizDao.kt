package com.hasitha.nihonNinja.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hasitha.nihonNinja.model.entities.AnswerEntity
import com.hasitha.nihonNinja.model.entities.QuestionEntity
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import com.hasitha.nihonNinja.model.entities.QuizEntity

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizEntity)

    @Query("SELECT * FROM quiz_table")
    fun getAllQuizzes(): LiveData<List<QuizEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Query("SELECT * FROM question_table WHERE quizId = :quizId")
    fun getQuestionsForQuiz(quizId: Int): LiveData<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswerEntity)

    @Query("SELECT * FROM answer_table WHERE questionId = :questionId")
    fun getAnswersForQuestion(questionId: Int): LiveData<List<AnswerEntity>>

    // Query to get Questions with their Answers for a particular Quiz
    @Transaction
    @Query("SELECT * FROM question_table WHERE quizId = :quizId")
    fun getQuestionsWithAnswersForQuiz(quizId: Int): LiveData<List<QuestionWithAnswers>>

    @Query("SELECT totalQuestions FROM quiz_table WHERE quizId = :quizId")
    suspend fun getTotalQuestionsForQuiz(quizId: Int): Int

}
