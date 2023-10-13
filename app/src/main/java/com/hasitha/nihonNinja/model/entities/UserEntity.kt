package com.hasitha.nihonNinja.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a user.
 *
 * @param id The unique identifier of the user.
 * @param name The name of the user.
 * @param email The email address of the user.
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val email: String,
)
