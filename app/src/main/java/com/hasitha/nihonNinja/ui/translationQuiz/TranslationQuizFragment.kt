package com.hasitha.nihonNinja.ui.translationQuiz

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.*
import com.google.android.material.snackbar.Snackbar
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.model.api.QuizResult
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TranslationQuizFragment : Fragment() {

    private val translationQuizViewModel: TranslationQuizViewModel by viewModels()

    private var buttonIdCounter = 0
    private var currentLineWidth = 0
    private var currentSentenceIndex = 0
    private var correctAnswerCount: Int = 0
    private var totalQuestions: Int = 0

    private lateinit var textView: TextView
    private lateinit var wordsFlexbox: FlexboxLayout
    private lateinit var buttonsFlexboxLayout: FlexboxLayout
    private lateinit var myWords: List<List<String>>

    private val listOfSentences = mutableListOf<String>()
    private val listOfAnswerOrders = mutableListOf<List<Int>>()

    private val buttonState: MutableMap<Int, Boolean> = mutableMapOf()
    private var selectedButtonIds: MutableList<Int> = mutableListOf()
    private lateinit var nextButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_translation_quiz, container, false)
        buttonsFlexboxLayout = view.findViewById(R.id.flexboxLayout)
        wordsFlexbox = view.findViewById(R.id.wordsFlexbox)
        nextButton = view.findViewById(R.id.nextButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = view.findViewById(R.id.trnalationquizsentnce)

        val quizId = arguments?.getInt("quizId") ?: return
        Log.d("+++ quizId", quizId.toString())

        translationQuizViewModel.fetchSentences(quizId)

        translationQuizViewModel.sentences2.observe(viewLifecycleOwner) { quizWithQuestionsAndAnswers ->
//            Log.d("+++ sentences observing", quizWithQuestionsAndAnswers.toString())
            totalQuestions = quizWithQuestionsAndAnswers.totalQuestions

            quizWithQuestionsAndAnswers.questionsWithAnswers.observe(viewLifecycleOwner) { list ->
//                Log.d("+++ sentences observing list", list.toString())

                list.forEach { questionWithAnswers ->
                    listOfSentences.add(questionWithAnswers.question.sentence) //Extract English/Sinhala Sentence
                    listOfAnswerOrders.add(questionWithAnswers.question.answerOrder) //Extract given answers
                }

                textView.text = listOfSentences[currentSentenceIndex]
                myWords = extractWordsFromResponse(list)
//                Log.d("+++ myWords", myWords.toString())
                populateFlexbox(myWords[currentSentenceIndex])

            }
//            Log.d("+++ listOfSentences", listOfSentences.toString())
//            Log.d("+++ listOfAnswerOrders", listOfAnswerOrders.toString())
//            Log.d("+++ myWords", myWords.toString())
        }

        translationQuizViewModel.currentSentenceIndex.observe(viewLifecycleOwner) { newIndex ->
            currentSentenceIndex = newIndex
        }

        translationQuizViewModel.isAnswerCorrect.observe(viewLifecycleOwner) { isCorrect ->
            if(isCorrect){
                correctAnswerCount++
                showSnackbar(view, isCorrect, "")
            } else {
                val answerList = myWords[currentSentenceIndex]
                val result = listOfAnswerOrders[currentSentenceIndex].map { answerList[it] }.joinToString(" ")
                showSnackbar(view, isCorrect, result)
            }
            selectedButtonIds.clear()
        }

        nextButton.setOnClickListener {
            translationQuizViewModel.evaluateUserAnswer(selectedButtonIds, currentSentenceIndex, listOfAnswerOrders)
        }
    }

    private fun questionIterate(){
        // Remove existing words
        wordsFlexbox.removeAllViews()

        translationQuizViewModel.nextQuestion()
//        Log.d("+++ myWords", myWords.toString())

        if (currentSentenceIndex < listOfSentences.size) {
            buttonsFlexboxLayout.removeAllViews() // Clear the existing buttons
            textView.text = listOfSentences[currentSentenceIndex]
            populateFlexbox(myWords[currentSentenceIndex]) // Populate with new sentence
//            Log.d("+++ listOfSentences index", listOfSentences[currentSentenceIndex])
//            Log.d("+++ listOfAnswerOrders index", listOfAnswerOrders[currentSentenceIndex].toString())

        } else {
            currentSentenceIndex = 0
            buttonsFlexboxLayout.removeAllViews()
            val userId = translationQuizViewModel.getUserId()
            val quizResult = QuizResult(userId, 1, correctAnswerCount)
            translationQuizViewModel.saveQuizResult(quizResult)
//            Log.d("+++ End of Quiz","+++ End of Quiz")
//            Log.d("+++ questionResults", questionResults.toString())
            val action = TranslationQuizFragmentDirections.actionTranslationQuizFragmentToQuizResultFragment(correctAnswerCount,totalQuestions)
            findNavController().navigate(action)
        }
    }

    private fun showSnackbar(view: View, isCorrect: Boolean, correctAnswer: String?) {

        val snackbarText = if (isCorrect) {
            "Answer is correct."
        } else {
            "Incorrect.\nCorrect answer: $correctAnswer"
        }
        val snackbar = Snackbar.make(view, snackbarText, Snackbar.LENGTH_INDEFINITE)
            .setAction("Continue") {
                questionIterate()
            }

        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
                nextButton.visibility = View.GONE
            }
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                nextButton.visibility = View.VISIBLE
            }
        })
        snackbar.show()
    }



    private fun extractWordsFromResponse(response: List<QuestionWithAnswers>): List<List<String>> {
        return response.map { questionWithAnswers ->
            questionWithAnswers.answers.map { it.word }
        }
    }

    private fun populateFlexbox(words: List<String>) {
//        Log.d("+++ populateFlexbox", "Function called with words: $words")
        buttonState.clear()
        buttonIdCounter = 0
        // Create a list to hold the button objects.
        val buttonList = mutableListOf<Button>()

        for (word in words) {
            val button = Button(context)
            button.text = word
            button.id = buttonIdCounter++
            buttonState[button.id] = false

            button.layoutParams = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )

            button.setOnClickListener {
                val isUsed = buttonState[button.id] ?: false
                if (!isUsed) {
                    addWordToFlexbox(button.text.toString())
                    selectedButtonIds.add(button.id)
                    buttonState[button.id] = true
                    button.visibility = View.GONE
                }
//                    Log.d("+++ ButtonOnClick", "Button with text ${button.text} was clicked!")
            }
            buttonList.add(button)
        }

        // Shuffle the button objects.
        val shuffledButtons = buttonList.shuffled()
//        Log.d("+++ populateFlexbox", "Shuffled button IDs: ${shuffledButtons.map { it.id }}")

        // Now add the shuffled buttons to the FlexboxLayout.
        for (shuffledButton in shuffledButtons) {
            buttonsFlexboxLayout.addView(shuffledButton)
        }
    }

    private fun addWordToFlexbox(word: String) {

        val metrics = DisplayMetrics()

        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val screenWidth = metrics.widthPixels

        val textView = TextView(context).apply {
            text = word
            textSize = 16f
            background = ContextCompat.getDrawable(context, R.drawable.textview_outline)
            setPadding(8, 4, 8, 4)

            val params = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 4, 4, 4)
            layoutParams = params
        }

        textView.measure(0, 0)
        val wordWidthWithMargins = textView.measuredWidth + 8

        if (currentLineWidth + wordWidthWithMargins > screenWidth) {
            currentLineWidth = 0
        }

        currentLineWidth += wordWidthWithMargins

        textView.setOnClickListener {
            val wordText = it as TextView
            wordsFlexbox.removeView(it)
            //buttonState[wordText.text.toString()] = false

            val correspondingButton = buttonsFlexboxLayout.children.iterator().asSequence()
                .filterIsInstance<Button>()
                .firstOrNull { it.text == wordText.text }

            if (correspondingButton != null) {
                buttonState[correspondingButton.id] = false // Use the ID instead of word to reset the state
                selectedButtonIds.remove(correspondingButton.id)

                //restore the button's appearance
                correspondingButton.visibility = View.VISIBLE
            }
            currentLineWidth -= wordWidthWithMargins
            if (currentLineWidth < 0) currentLineWidth = 0
        }
        wordsFlexbox.addView(textView)
    }
}