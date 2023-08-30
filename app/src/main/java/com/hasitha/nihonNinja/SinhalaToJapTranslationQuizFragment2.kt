package com.hasitha.nihonNinja

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class SinhalaToJapTranslationQuizFragment2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sinhala_to_jap_translation_quiz2, container, false)

        val answersLayout: LinearLayout = view.findViewById(R.id.answerButtonsLayout)
        val selectedWordsLayout: LinearLayout = view.findViewById(R.id.selectedWordsLayout)
        //val jsonString = "[{\"text\": \"せんせい\"}, {\"text\": \"いま\"}, {\"text\": \"なん\"}, {\"text\": \"じ\"}, {\"text\": \"です\"}, {\"text\": \"か\"}]"

        val answers = arrayOf("せんせい", "いま", "なん","じ", "です", "か")

        answers.forEach { answer ->
            val button = Button(context)
            button.text = answer
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            button.setOnClickListener {
                val selectedWord = TextView(context)
                selectedWord.text = answer
                selectedWord.setOnClickListener {
                    selectedWordsLayout.removeView(selectedWord)
                }
                selectedWordsLayout.addView(selectedWord)
            }
            answersLayout.addView(button)
        }

        return view
    }
}