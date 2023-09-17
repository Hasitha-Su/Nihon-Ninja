package com.hasitha.nihonNinja.ui.translationQuiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import com.hasitha.nihonNinja.repository.QuestionRepository
import com.hasitha.nihonNinja.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TranslationQuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private val _sentences = MutableLiveData<List<QuestionWithAnswers>>()
    val sentences: LiveData<List<QuestionWithAnswers>> get() = _sentences

    fun fetchSentences(quizId: Int) {
        //Hard coded ID
        quizRepository.getQuestionsWithAnswersForQuiz(1).observeForever { questions ->
            _sentences.value = questions
        }
    }
}
