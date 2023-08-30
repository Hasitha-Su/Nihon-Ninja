package com.hasitha.nihonNinja.ui.translationQuiz

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.flexbox.*
import com.hasitha.nihonNinja.R

class SinhalaToJapTranslationQuizFragment : Fragment() {

    private lateinit var wordsFlexbox: FlexboxLayout
    private var currentLineWidth = 0
    //private val currentLineWords = mutableListOf<TextView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sinhala_to_jap_translation_quiz, container, false)
        wordsFlexbox = view.findViewById(R.id.wordsFlexbox)

        //"この サンプル の センテンス は デモンストレーション のため だけ のもの です
        //val sentence = "This sample sentence is one only for demonstration purpose"
        val sentence = "この サンプル の センテンス は デモンストレーション のため だけ のもの です"
        val words = sentence.split(" ")

        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val screenWidth = metrics.widthPixels

        val estimatedLines = estimateLinesNeeded(words, screenWidth)
        repeat(estimatedLines) {
            addBlackLine()
        }

        val buttonsFlexboxLayout: FlexboxLayout = view.findViewById(R.id.flexboxLayout)
        buttonsFlexboxLayout.flexDirection = FlexDirection.ROW
        buttonsFlexboxLayout.flexWrap = FlexWrap.WRAP
        buttonsFlexboxLayout.alignItems = AlignItems.CENTER
        buttonsFlexboxLayout.justifyContent = JustifyContent.CENTER

        //"せんせい", "いま", "なん", "じ", "です", "か"
        val answers = arrayOf("この", "サンプル", "の", "センテンス", "は", "デモンストレーション", "のため", "だけ", "のもの", "です")
        for (answer in answers) {
            val button = Button(context)
            button.text = answer
            button.layoutParams = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )

            button.setOnClickListener {
                addWordToFlexbox(button.text.toString())
//                addWordToFlexbox(button.text.toString(), button)
                Log.d("+++ ButtonOnClick", "Button with text ${button.text} was clicked!")
            }

            buttonsFlexboxLayout.addView(button)
        }
        return view
    }

    private fun estimateLinesNeeded(words: List<String>, screenWidth: Int): Int {
        var lines = 1
        var currentLineWidth = 0
        val spaceTextView = TextView(context)
        spaceTextView.text = " "
        spaceTextView.measure(0, 0)
        val spaceWidth = spaceTextView.measuredWidth

        words.forEach { word ->
            val textView = TextView(context).apply {
                text = word
                textSize = 16f
            }
            textView.measure(0, 0)
            val wordWidthWithMargins = textView.measuredWidth + 8

            if (currentLineWidth + wordWidthWithMargins + (lines * spaceWidth) > screenWidth) {
                lines++
                currentLineWidth = 0
            }
            currentLineWidth += wordWidthWithMargins
        }
        return lines
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
            wordsFlexbox.removeView(it)
            currentLineWidth -= wordWidthWithMargins
            if (currentLineWidth < 0) currentLineWidth = 0
        }
        wordsFlexbox.addView(textView)
    }

    private fun addBlackLine() {
        val blackLine = View(context).apply {
            layoutParams = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.MATCH_PARENT,
                2
            )
            setBackgroundColor(Color.BLACK)
        }
        wordsFlexbox.addView(blackLine)
    }
}
