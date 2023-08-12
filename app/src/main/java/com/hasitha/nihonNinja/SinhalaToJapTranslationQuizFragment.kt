package com.hasitha.nihonNinja

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SinhalaToJapTranslationQuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SinhalaToJapTranslationQuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val constraintLayout: ConstraintLayout = view.findViewById(R.id.frameLayout4)
//
//        val numButtons = 5  // The number of buttons you want to create
//
//        for (i in 1..numButtons) {
//            val button = Button(context)
//            button.id = View.generateViewId()
//            button.text = "Button $i"
//
//            val params = ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//            )
//            // You can set constraints for the button programmatically here
//            if (i > 1) {
//                params.topToBottom = constraintLayout.getChildAt(i - 2).id
//                params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
//            } else {
//                params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
//                params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
//            }
//
//            button.layoutParams = params
//            constraintLayout.addView(button)
//
//
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sinhala_to_jap_translation_quiz, container, false)
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sinhala_to_jap_translation_quiz, container, false)
//    }

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
//     Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_sinhala_to_jap_translation_quiz, container, false)

    val constraintLayout: ConstraintLayout = view.findViewById(R.id.frameLayout4)  // Assuming frameLayout4 is your ConstraintLayout's ID

    val numButtons = 5  // The number of buttons you want to create

    for (i in 1..numButtons) {
        val button = Button(context)
        button.id = View.generateViewId()
        button.text = "Button $i"

        val params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        // You can set constraints for the button programmatically here
        if (i > 1) {
            params.topToBottom = constraintLayout.getChildAt(i - 2).id
            params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        } else {
            params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        }

        button.layoutParams = params
        constraintLayout.addView(button)
    }

    return view

}


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SinhalaToJapTranslationQuizFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SinhalaToJapTranslationQuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}