package com.hasitha.nihonNinja.ui.home

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.hasitha.nihonNinja.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeFragmentTest {

    @Mock
    private lateinit var navController: NavController

    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_NihonNinja)
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun whenLoginButtonClicked_thenNavigateToLoginFragment() {
        onView(withId(R.id.loginBtn)).perform(click())
        verify(navController).navigate(R.id.action_homeFragment_to_loginFragment)
    }

    @Test
    fun whenButton4Clicked_thenNavigateToUserSignUpFragment() {
        onView(withId(R.id.button4)).perform(click())
        verify(navController).navigate(R.id.action_homeFragment_to_userSignUpFragment)
    }
}

