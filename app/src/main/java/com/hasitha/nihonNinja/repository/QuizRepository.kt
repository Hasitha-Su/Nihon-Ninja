package com.hasitha.nihonNinja.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hasitha.nihonNinja.data.local.QuizDao
import com.hasitha.nihonNinja.data.remote.QuizApiService
import com.hasitha.nihonNinja.model.entities.AnswerEntity
import com.hasitha.nihonNinja.model.entities.QuestionEntity
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import com.hasitha.nihonNinja.model.entities.QuizEntity
import com.hasitha.nihonNinja.model.entities.QuizWithQuestionsAndAnswers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepository(
    private val quizDao: QuizDao,
    private val quizApiService: QuizApiService) {

    // Fetches the quiz data from the API and saves it to the local database
    suspend fun refreshQuizData() {
        withContext(Dispatchers.IO) {
            val quizResponse = quizApiService.getQuizById()
            Log.d("+++ quizResponse", quizResponse.toString())

            val quiz = QuizEntity(quizResponse.quizId, quizResponse.quizName, quizResponse.totalQuestions)
            quizDao.insertQuiz(quiz)

            // Insert each Question and associated Answers
            quizResponse.questions.forEach { questionResponse ->
                val question = QuestionEntity(
                    questionId = questionResponse.questionId,
                    quizId = quizResponse.quizId,
                    sentence = questionResponse.sentence,
                    answerOrder = questionResponse.answerOrder
                )
                quizDao.insertQuestion(question)

                questionResponse.answers.forEach { answerResponse ->
                    val answer = AnswerEntity(
                        order = answerResponse.order,
                        word = answerResponse.word,
                        questionId = questionResponse.questionId
                    )

                    quizDao.insertAnswer(answer)
                }
            }
        }
    }

    //Get quiz questions and answers
    fun getQuestionsWithAnswersForQuiz(quizId: Int): LiveData<List<QuestionWithAnswers>> {
        return quizDao.getQuestionsWithAnswersForQuiz(quizId)
    }

    fun getQuizWithQuestionsAndAnswers(quizId: Int): LiveData<QuizWithQuestionsAndAnswers> = liveData {
        val questionsWithAnswers = quizDao.getQuestionsWithAnswersForQuiz(quizId)
        val totalQuestions = quizDao.getTotalQuestionsForQuiz(quizId) // You'll need to create this DAO function
        Log.d("+++ totalQuestions", totalQuestions.toString())
        emit(QuizWithQuestionsAndAnswers(totalQuestions, questionsWithAnswers))
    }


    fun getAllQuizzes(): LiveData<List<QuizEntity>> {
        return quizDao.getAllQuizzes()
    }


        /*
         //Other CRUD operations
        suspend fun insertQuiz(quiz: QuizEntity) {
            quizDao.insertQuiz(quiz)
        }

        suspend fun insertQuestion(question: QuestionEntity) {
            quizDao.insertQuestion(question)
        }

        suspend fun insertAnswer(answer: AnswerEntity) {
            quizDao.insertAnswer(answer)
        }
        }

        */
}
