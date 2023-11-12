package com.hasitha.nihonNinja.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hasitha.nihonNinja.model.entities.UserEntity

/**
 * Data Access Object (DAO) for User-related database operations.
 */
@Dao
interface UserDao {

    /**
     * Saves a user entity into the database.
     *
     * @param user The user entity to save.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    /**
     * Retrieves a user entity by its ID from the database.
     *
     * @param userId The ID of the user.
     * @return The user entity if found, or null if not found.
     */
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUser(userId: Long): UserEntity?
}