package com.hasitha.nihonNinja.ui.translationQuiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasitha.nihonNinja.model.api.QuizResult
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import com.hasitha.nihonNinja.model.entities.QuizWithQuestionsAndAnswers
import com.hasitha.nihonNinja.repository.QuestionRepository
import com.hasitha.nihonNinja.repository.QuizRepository
import com.hasitha.nihonNinja.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationQuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

    //TODO - Remove lateinit
    lateinit var sentences2: LiveData<QuizWithQuestionsAndAnswers>
    val currentSentenceIndex: MutableLiveData<Int> = MutableLiveData(0)

    fun fetchSentences(quizId: Int) {
        //TODO - Remove hardcoded ID
        sentences2 = quizRepository.getQuizWithQuestionsAndAnswers(1)
    }

    fun nextQuestion() {
        currentSentenceIndex.value = (currentSentenceIndex.value ?: 0) + 1
    }

    fun saveQuizResult(quizResult: QuizResult) = viewModelScope.launch {
        quizRepository.saveQuizResult(quizResult)
    }

    fun getUserId(): Long = sharedPrefManager.getUserId()
}
