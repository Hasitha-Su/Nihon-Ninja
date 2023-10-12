package com.hasitha.nihonNinja.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hasitha.nihonNinja.model.entities.QuizEntity
import com.hasitha.nihonNinja.repository.QuizRepository
import com.hasitha.nihonNinja.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    quizRepository: QuizRepository,
    sharedPrefManager: SharedPrefManager

) : ViewModel() {

    val allQuizzes: LiveData<List<QuizEntity>> = quizRepository.getAllQuizzes()
    private val userName: String = sharedPrefManager.getUserName() ?: "User"

    val greeting: LiveData<String> = MutableLiveData<String>().apply {
        value = "Hi $userName"
        Log.d("+++ DashboardViewModel", "Greeting: $value")
    }

    val sayHi: LiveData<String> = MutableLiveData<String>().apply {
        value = getGreetingMessage()
    }

    private fun getGreetingMessage(): String {
        val c = Calendar.getInstance()

        return when (c.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            else -> "How are you ?"
        }
    }
}
