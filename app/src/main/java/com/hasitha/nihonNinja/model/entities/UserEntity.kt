package com.hasitha.nihonNinja.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
//    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @PrimaryKey
    val id: Long,
    val name: String,
    val email: String,
)
