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
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.*
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.model.entities.QuestionResult
import com.hasitha.nihonNinja.model.entities.QuestionWithAnswers
import dagger.hilt.android.AndroidEntryPoint
import java.util.Arrays


@AndroidEntryPoint
class TranslationQuizFragment : Fragment() {

    // use this ?
    private val translationQuizViewModel: TranslationQuizViewModel by viewModels()

    private var buttonIdCounter = 0

    private var currentLineWidth = 0
    private var currentSentenceIndex = 0

    private lateinit var wordsFlexbox: FlexboxLayout
    private lateinit var buttonsFlexboxLayout: FlexboxLayout

    private lateinit var myWords: List<List<String>>
    private var sentences: List<QuestionWithAnswers> = listOf()
    private val buttonState: MutableMap<Int, Boolean> = mutableMapOf()

    private val selectedButtonIds: MutableList<Int> = mutableListOf()
    private val selectedButtonIdsForAllQuestions: MutableList<MutableList<Int>> = mutableListOf()

    private val listOfSentences = mutableListOf<String>()
    private val listOfAnswerOrders = mutableListOf<List<Int>>()
    private val questionResults: MutableList<QuestionResult> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_translation_quiz, container, false)
        buttonsFlexboxLayout = view.findViewById(R.id.flexboxLayout)
        wordsFlexbox = view.findViewById(R.id.wordsFlexbox)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextButton: Button = view.findViewById(R.id.nextButton)
        Log.d("+++ Lifecycle", "onViewCreated called")

        val quizId = arguments?.getInt("quizId") ?: return

        translationQuizViewModel.fetchSentences(quizId)
        Log.d("+++ quizId", quizId.toString())
//        Log.d("+++ sentences", sentences.toString())

        translationQuizViewModel.sentences.observe(viewLifecycleOwner) { newSentences ->
//            Log.d("+++ sentences observing", sentences.toString())
            Log.d("+++ newSentences", newSentences.toString())

            sentences = newSentences
            myWords = extractWordsFromResponse(sentences)

            // Extracting and populating the lists
            newSentences.forEach { questionWithAnswers ->
                listOfSentences.add(questionWithAnswers.question.sentence) //Extract English/Sinhala Sentence and show it
                listOfAnswerOrders.add(questionWithAnswers.question.answerOrder) //Extract given answers
            }

            Log.d("+++ listOfSentences", listOfSentences.toString())
            Log.d("+++ listOfAnswerOrders", listOfAnswerOrders.toString())

            Log.d("+++ myWords", myWords.toString())

            populateFlexbox(myWords[currentSentenceIndex])
            Log.d("+++ listOfSentences index", listOfSentences[currentSentenceIndex])
            Log.d("+++ listOfAnswerOrders index", listOfAnswerOrders[currentSentenceIndex].toString())
        }


        //NEXT BUTTON
        nextButton.setOnClickListener {

            //TODO - Add validation for NEXT button - no next with empty answer
            //TODO - Evaluate User answer with given answer
            val isAnswerCorrect = selectedButtonIds == listOfAnswerOrders[currentSentenceIndex]
            questionResults.add(QuestionResult(questionNumber = currentSentenceIndex + 1, isCorrect = isAnswerCorrect))

            Log.d("+++ selectedButtonIds", selectedButtonIds.toString())

            // Remove existing words
            wordsFlexbox.removeAllViews()

            // Increment the sentence index
            currentSentenceIndex++

//            selectedButtonIdsForAllQuestions.add(mutableListOf())
            selectedButtonIdsForAllQuestions.add(ArrayList(selectedButtonIds))
            selectedButtonIds.clear()

            Log.d("+++ selectedButtonIdsForAllQuestions", selectedButtonIdsForAllQuestions.toString())

            // Check if we've reached the end of the sentences array

            //TODO - When reached end of array, show total of the score to the user in a new Screen
            //TODO - Navigate to results screen
            //TODO - Save the score in User DB - before showing results
            //TODO - QuizId is not passed properly
            //TODO - change "NEXT" text to "Submit"

            if (currentSentenceIndex < sentences.size) {
                buttonsFlexboxLayout.removeAllViews() // Clear the existing buttons
                populateFlexbox(myWords[currentSentenceIndex]) // Populate with new sentence
                Log.d("+++ listOfSentences index", listOfSentences[currentSentenceIndex])
                Log.d("+++ listOfAnswerOrders index", listOfAnswerOrders[currentSentenceIndex].toString())

            } else {
                currentSentenceIndex = 0
                buttonsFlexboxLayout.removeAllViews()
                //populateFlexbox(myWords[currentSentenceIndex])
                Log.d("+++ End of Quiz","+++ End of Quiz")

//                for (result in questionResults) {
//                    Log.d("QuestionResult", "Question ${result.questionNumber} is correct: ${result.isCorrect}")
//                }

                Log.d("+++ questionResults", questionResults.toString())
                questionResults

                //TODO - Clear everything
                //TODO - Pass number correct answers
                findNavController().navigate(R.id.action_sinhalaToJapTranslationQuizFragment3_to_quizResultFragment)
            }
        }
    }

    private fun extractWordsFromResponse(response: List<QuestionWithAnswers>): List<List<String>> {
        return response.map { questionWithAnswers ->
            questionWithAnswers.answers.map { it.word }
        }
    }

    private fun populateFlexbox(words: List<String>) {
        Log.d("+++ populateFlexbox", "Function called with words: $words")


        buttonState.clear()
        buttonIdCounter = 0
        // Create a list to hold the button objects.
        val buttonList = mutableListOf<Button>()

//        for (word in words) {
        for (word in words) {

//            buttonState[word] = false

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
                    //addWordToFlexbox(word)
                    addWordToFlexbox(button.text.toString())
//                    selectedButtonIdsForAllQuestions[currentSentenceIndex].add(button.id)
                    selectedButtonIds.add(button.id)
                    buttonState[button.id] = true
                    button.visibility = View.GONE
                }
//                addWordToFlexbox(button.text.toString())
//                    Log.d("+++ ButtonOnClick", "Button with text ${button.text} was clicked!")
            }

            buttonList.add(button)
        }

        //buttonsFlexboxLayout.addView(button)
        // Shuffle the button objects.
        val shuffledButtons = buttonList.shuffled()
        Log.d("+++ populateFlexbox", "Shuffled button IDs: ${shuffledButtons.map { it.id }}")


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
//                selectedButtonIdsForAllQuestions[currentSentenceIndex].remove(correspondingButton.id)
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

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//
//        val view = inflater.inflate(R.layout.fragment_translation_quiz, container, false)

//        buttonsFlexboxLayout = view.findViewById(R.id.flexboxLayout)

        // Initialize with the first sentence
//        if (sentences.isNotEmpty()) {
//            populateFlexbox(sentences[currentSentenceIndex])
//        }

        /*
        val nextButton: Button = view.findViewById(R.id.nextButton)

        nextButton.setOnClickListener {

            // Increment the sentence index
            currentSentenceIndex++

            // Check if we've reached the end of the sentences array
            if (currentSentenceIndex < sentences.size) {

                // Clear the existing buttons
                buttonsFlexboxLayout.removeAllViews()

                // Populate with new sentence
                populateFlexbox(sentences[currentSentenceIndex])

            } else {

                // Do something when all sentences have been shown
                // Perhaps go back to the first sentence
                currentSentenceIndex = 0
                buttonsFlexboxLayout.removeAllViews()
                populateFlexbox(sentences[currentSentenceIndex])
            }
        }

*/

/*
        private fun extractWordsFromResponse(response: List<QuestionWithAnswers>): List<String> {
            return if (currentSentenceIndex in response.indices) {
                val currentQuestionWithAnswers = response[currentSentenceIndex]
                currentQuestionWithAnswers.answers.map { it.word }
            } else {
                emptyList()
            }
        }
 */


/*
        wordsFlexbox = view.findViewById(R.id.wordsFlexbox)

        //"この サンプル の センテンス は デモンストレーション のため だけ のもの です
        //val sentence = "This sample sentence is one only for demonstration purpose"
        //val sentence = "この サンプル の センテンス は デモンストレーション のため だけ のもの です"
        //val words = sentence.split(" ")

        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val screenWidth = metrics.widthPixels

//        val estimatedLines = estimateLinesNeeded(words, screenWidth)
//        repeat(estimatedLines) {
//            addBlackLine()
//        }

        val buttonsFlexboxLayout: FlexboxLayout = view.findViewById(R.id.flexboxLayout)

        buttonsFlexboxLayout.flexDirection = FlexDirection.ROW
        buttonsFlexboxLayout.flexWrap = FlexWrap.WRAP
        buttonsFlexboxLayout.alignItems = AlignItems.CENTER
        buttonsFlexboxLayout.justifyContent = JustifyContent.CENTER

        //"せんせい", "いま", "なん", "じ", "です", "か"
        //val answers = arrayOf("この", "サンプル", "の", "センテンス", "は", "デモンストレーション", "のため", "だけ", "のもの", "です")


//        for (answer in answers) {
//            val button = Button(context)
//            button.text = answer
//            button.layoutParams = FlexboxLayout.LayoutParams(
//                FlexboxLayout.LayoutParams.WRAP_CONTENT,
//                FlexboxLayout.LayoutParams.WRAP_CONTENT
//            )
//
//            button.setOnClickListener {
//                addWordToFlexbox(button.text.toString())
////                addWordToFlexbox(button.text.toString(), button)
//                Log.d("+++ ButtonOnClick", "Button with text ${button.text} was clicked!")
//            }
//
//            buttonsFlexboxLayout.addView(button)
//        }
        return view
    }


 */


//    private fun addBlackLine() {
//        val blackLine = View(context).apply {
//            layoutParams = FlexboxLayout.LayoutParams(
//                FlexboxLayout.LayoutParams.MATCH_PARENT,
//                2
//            )
//            setBackgroundColor(Color.BLACK)
//        }
//        wordsFlexbox.addView(blackLine)
//    }


//    private fun populateFlexbox(sentence: String) {
//        for (word in sentence) {
//            val button = Button(context)
//            button.text = word
//            button.layoutParams = FlexboxLayout.LayoutParams(
//                FlexboxLayout.LayoutParams.WRAP_CONTENT,
//                FlexboxLayout.LayoutParams.WRAP_CONTENT
//            )
//
//            button.setOnClickListener {
//                addWordToFlexbox(button.text.toString())
//                Log.d("+++ ButtonOnClick", "Button with text ${button.text} was clicked!")
//            }
//
//            buttonsFlexboxLayout.addView(button)
//        }
//    }

    //    private fun estimateLinesNeeded(words: List<String>, screenWidth: Int): Int {
//        var lines = 1
//        var currentLineWidth = 0
//        val spaceTextView = TextView(context)
//        spaceTextView.text = " "
//        spaceTextView.measure(0, 0)
//        val spaceWidth = spaceTextView.measuredWidth
//
//        words.forEach { word ->
//            val textView = TextView(context).apply {
//                text = word
//                textSize = 16f
//            }
//            textView.measure(0, 0)
//            val wordWidthWithMargins = textView.measuredWidth + 8
//
//            if (currentLineWidth + wordWidthWithMargins + (lines * spaceWidth) > screenWidth) {
//                lines++
//                currentLineWidth = 0
//            }
//            currentLineWidth += wordWidthWithMargins
//        }
//        return lines
//    }

}
