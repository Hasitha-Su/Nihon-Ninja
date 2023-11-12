package com.hasitha.nihonNinja.util

class Common {

    companion object {
        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPassword(password: String): Boolean {
            val regex = "^(?=.*[0-9])" +     // At least one digit
                    "(?=.*[a-z])" +         // At least one lowercase letter
                    "(?=.*[A-Z])" +         // At least one uppercase letter
                    "(?=.*[@#$%^&+=])" +    // At least one special character
                    "(?=\\S+$)" +           // No whitespace
                    ".{8,}$"                // At least 8 characters

            return password.matches(regex.toRegex())
        }
    }
}