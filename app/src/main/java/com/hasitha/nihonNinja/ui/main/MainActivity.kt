package com.hasitha.nihonNinja.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hasitha.nihonNinja.R
import com.hasitha.nihonNinja.repository.QuizRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var quizRepository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.loginFragment,
                R.id.userSignUpFragment,
                R.id.translationQuizFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

        bottomNavigationView.setOnItemSelectedListener  { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    navController.navigate(R.id.dashboardFragment)
                    true
                }
                R.id.action_dashboard -> {
                    navController.navigate(R.id.leaderBoardFragment)
                    true
                }
                R.id.action_profile -> {
                     navController.navigate(R.id.userProfileFragment)
                    true
                }
                else -> false
            }
        }

        lifecycleScope.launch {
            quizRepository.refreshQuizData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }
}
