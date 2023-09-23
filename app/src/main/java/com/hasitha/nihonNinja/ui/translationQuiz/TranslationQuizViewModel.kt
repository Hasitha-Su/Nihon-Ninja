package com.hasitha.nihonNinja.ui.translationQuiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import com.hasitha.nihonNinja.model.entities.QuizWithQuestionsAndAnswers
import com.hasitha.nihonNinja.repository.QuestionRepository
import com.hasitha.nihonNinja.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TranslationQuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    //TODO - Remove lateinit
    lateinit var sentences2: LiveData<QuizWithQuestionsAndAnswers>
    //lateinit var sentences: LiveData<List<QuestionWithAnswers>>
    val currentSentenceIndex: MutableLiveData<Int> = MutableLiveData(0)

//    private val _currentWords = MutableLiveData<List<String>>()
//    val currentWords: LiveData<List<String>> get() = _currentWords


    fun fetchSentences(quizId: Int) {
        //TODO - Remove hardcoded ID
//        sentences = quizRepository.getQuestionsWithAnswersForQuiz(1)
        sentences2 = quizRepository.getQuizWithQuestionsAndAnswers(1)
    }

    fun nextQuestion() {
        currentSentenceIndex.value = (currentSentenceIndex.value ?: 0) + 1
    }

//    fun updateWords(newWords: List<String>) {
//        _currentWords.value = newWords
//    }



    /*
    private val _sentences = MutableLiveData<List<QuestionWithAnswers>>()
    val sentences: LiveData<List<QuestionWithAnswers>> get() = _sentences

    fun fetchSentences(quizId: Int) {
        //Hard coded ID
        quizRepository.getQuestionsWithAnswersForQuiz(1).observeForever { questions ->
            _sentences.value = questions
        }
    }

     */
}
