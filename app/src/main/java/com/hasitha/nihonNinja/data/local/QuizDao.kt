package com.hasitha.nihonNinja.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hasitha.nihonNinja.model.entities.AnswerEntity
import com.hasitha.nihonNinja.model.entities.QuestionEntity
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import com.hasitha.nihonNinja.model.entities.QuizEntity

/**
 * Data Access Object (DAO) for Quiz-related database operations.
 */
@Dao
interface QuizDao {

    /**
     * Inserts a quiz entity into the database.
     *
     * @param quiz The quiz entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizEntity)

    /**
     * Retrieves all quizzes from the database.
     *
     * @return A LiveData list of all quiz entities.
     */
    @Query("SELECT * FROM quiz_table")
    fun getAllQuizzes(): LiveData<List<QuizEntity>>


    /**
     * Inserts a question entity into the database.
     *
     * @param question The question entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)


    /**
     * Retrieves all questions for a specific quiz from the database.
     *
     * @param quizId The ID of the quiz.
     * @return A LiveData list of question entities for the specified quiz.
     */
    @Query("SELECT * FROM question_table WHERE quizId = :quizId")
    fun getQuestionsForQuiz(quizId: Int): LiveData<List<QuestionEntity>>


    /**
     * Inserts an answer entity into the database.
     *
     * @param answer The answer entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswerEntity)


    /**
     * Retrieves all answers for a specific question from the database.
     *
     * @param questionId The ID of the question.
     * @return A LiveData list of answer entities for the specified question.
     */
    @Query("SELECT * FROM answer_table WHERE questionId = :questionId")
    fun getAnswersForQuestion(questionId: Int): LiveData<List<AnswerEntity>>


    /**
     * Retrieves questions with their associated answers for a specific quiz.
     *
     * @param quizId The ID of the quiz.
     * @return A LiveData list of QuestionWithAnswers entities for the specified quiz.
     */
    @Transaction
    @Query("SELECT * FROM question_table WHERE quizId = :quizId")
    fun getQuestionsWithAnswersForQuiz(quizId: Int): LiveData<List<QuestionWithAnswers>>


    /**
     * Retrieves the total number of questions for a specific quiz.
     *
     * @param quizId The ID of the quiz.
     * @return The total number of questions for the specified quiz.
     */
    @Query("SELECT totalQuestions FROM quiz_table WHERE quizId = :quizId")
    suspend fun getTotalQuestionsForQuiz(quizId: Int): Int

}
