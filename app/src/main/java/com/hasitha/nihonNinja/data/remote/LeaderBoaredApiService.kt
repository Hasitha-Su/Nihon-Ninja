package com.hasitha.nihonNinja.data.remote

import com.hasitha.nihonNinja.model.api.LeaderBoardUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface LeaderBoaredApiService {
    @GET("leaderboard")
    suspend fun getLeaderBoard(): List<LeaderBoardUserResponse>

}
