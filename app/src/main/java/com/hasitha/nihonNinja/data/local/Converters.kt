package com.hasitha.nihonNinja.data.local

import androidx.room.TypeConverter

/**
 * Room type converters for handling data type conversions in the database.
 */
object Converters {
    /**
     * Converts a list of integers to a comma-separated string.
     *
     * @param value The list of integers to be converted.
     * @return A comma-separated string representation of the list.
     */
    @TypeConverter
    fun listToString(value: List<Int>?): String? {
        return value?.joinToString(",")
    }

    /**
     * Converts a comma-separated string to a list of integers.
     *
     * @param value The string to be converted.
     * @return A list of integers extracted from the string.
     */
    @TypeConverter
    fun stringToList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() }
    }
}
