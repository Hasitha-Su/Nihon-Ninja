package com.hasitha.nihonNinja.data.local

import androidx.room.TypeConverter

object Converters {
    @TypeConverter
    fun listToString(value: List<Int>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun stringToList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() }
    }
}
