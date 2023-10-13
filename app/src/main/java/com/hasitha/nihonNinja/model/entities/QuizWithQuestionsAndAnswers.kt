package com.hasitha.nihonNinja.model.entities

import androidx.lifecycle.LiveData

/**
 * Data class representing a quiz along with its associated questions and answers.
 *
 * @property totalQuestions The total number of questions in the quiz.
 * @property questionsWithAnswers A [LiveData] containing a list of [QuestionWithAnswers] objects
 * representing the questions and their associated answers for the quiz.
 */
data class QuizWithQuestionsAndAnswers(
    val totalQuestions: Int,
    val questionsWithAnswers: LiveData<List<QuestionWithAnswers>>
)
