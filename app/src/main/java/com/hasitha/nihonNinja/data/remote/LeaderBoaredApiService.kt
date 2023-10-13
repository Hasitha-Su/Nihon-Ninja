package com.hasitha.nihonNinja.data.remote

import retrofit2.http.GET
import com.hasitha.nihonNinja.model.api.LeaderBoardUserResponse

/**
 * Retrofit API service interface for leaderboard-related API requests.
 */
interface LeaderBoaredApiService {

    /**
     * Sends an HTTP GET request to retrieve the leaderboard data.
     *
     * @return A list of [LeaderBoardUserResponse] objects representing leaderboard user data.
     */
    @GET("/leaderboard")
    suspend fun getLeaderBoard(): List<LeaderBoardUserResponse>
}
