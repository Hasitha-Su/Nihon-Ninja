package com.hasitha.nihonNinja.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefManager @Inject constructor(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: SharedPrefManager? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: SharedPrefManager(context.applicationContext).also { instance = it }
            }
    }

    fun saveUserId(userId: Long) {
        with(sharedPref.edit()) {
            putLong("user_id", userId)
            apply()
        }
    }

    fun getUserId(): Long {
        return sharedPref.getLong("user_id", -1) // return -1 if no id is found.
    }
}
