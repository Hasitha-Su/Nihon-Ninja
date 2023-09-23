package com.hasitha.nihonNinja.ui.quizResult

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.model.entities.QuestionResult
import com.hasitha.nihonNinja.ui.dashboard.DashboardViewModel

class QuizResultFragment : Fragment() {


    private lateinit var viewModel: QuizResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_result, container, false)
    }





//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(QuizResultViewModel::class.java)
//
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: QuizResultFragmentArgs by navArgs()

        val param1 = args.param1
        val param2 = args.param2

        Log.d("+++ param1", param1.toString() )
        Log.d("+++ param2", param2.toString())

    }
}
