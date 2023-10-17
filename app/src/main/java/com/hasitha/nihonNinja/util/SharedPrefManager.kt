package com.hasitha.nihonNinja.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val encryptedPrefs = EncryptedSharedPreferences.create(
        "secure_shared_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String) {
        encryptedPrefs.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return encryptedPrefs.getString("token", null)
    }

    fun clearToken() {
        with(encryptedPrefs.edit()) {
            remove("token")
            apply()
        }
    }

    fun saveUserId(userId: Long) {
        with(sharedPref.edit()) {
            putLong("user_id", userId)
            apply()
        }
    }

    fun getUserId(): Long {
        return sharedPref.getLong("user_id", -1)
    }

    fun saveUserName(userName: String) {
        with(sharedPref.edit()) {
            putString("user_name", userName)
            apply()
        }
    }

    fun getUserName(): String {
        return sharedPref.getString("user_name", "") ?: ""
    }

    fun clearPreferences() {
        with(sharedPref.edit()) {
            clear()
            apply()
        }
        with(encryptedPrefs.edit()) {
            clear()
            apply()
        }
    }
}

