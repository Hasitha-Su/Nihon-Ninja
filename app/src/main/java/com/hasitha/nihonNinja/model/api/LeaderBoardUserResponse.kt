package com.hasitha.nihonNinja.model.api

/**
 * Data class representing a user's response in the leaderboard.
 *
 * @property name The name of the user.
 * @property score The score achieved by the user.
 */
class LeaderBoardUserResponse(
    val name: String,
    val score: Int
)