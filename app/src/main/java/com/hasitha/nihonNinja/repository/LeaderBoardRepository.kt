package com.hasitha.nihonNinja.repository

import javax.inject.Inject
import com.hasitha.nihonNinja.data.remote.LeaderBoaredApiService
import com.hasitha.nihonNinja.model.api.LeaderBoardUserResponse

/**
 * Repository class for accessing leaderboard data.
 *
 * @param apiService The API service responsible for fetching leaderboard data.
 */
class LeaderBoardRepository @Inject constructor(
    private val apiService: LeaderBoaredApiService
) {

    /**
     * Fetches the leaderboard data.
     *
     * @return A list of [LeaderBoardUserResponse] objects representing leaderboard users.
     */
    suspend fun getLeaderBoard(): List<LeaderBoardUserResponse> {
        return apiService.getLeaderBoard()
    }
}
