package com.hasitha.nihonNinja.ui.dashboard

import com.hasitha.nihonNinja.repository.QuizRepository
import com.hasitha.nihonNinja.util.SharedPrefManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {

    @Mock
    lateinit var quizRepository: QuizRepository

    @Mock
    lateinit var sharedPrefManager: SharedPrefManager

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp() {
        `when`(sharedPrefManager.getUserName()).thenReturn("TestUser")
        viewModel = DashboardViewModel(quizRepository, sharedPrefManager)
    }

    @Test
    fun testGreetingValue() {
        assert(viewModel.greeting.value == "Hi TestUser")
    }

}