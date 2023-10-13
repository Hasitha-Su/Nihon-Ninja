package com.hasitha.nihonNinja.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
//    val username: String,
    val name: String,
    val email: String,
    // other fields here…
)
