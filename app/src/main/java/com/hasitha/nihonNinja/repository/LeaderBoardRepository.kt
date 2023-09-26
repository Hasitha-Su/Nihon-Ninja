package com.hasitha.nihonNinja.repository

import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import com.hasitha.nihonNinja.data.remote.LeaderBoaredApiService
import com.hasitha.nihonNinja.model.api.LeaderBoardUserResponse
import javax.inject.Inject

class LeaderBoardRepository @Inject constructor(
    private val apiService: LeaderBoaredApiService
) {
    suspend fun getLeaderBoard(): List<LeaderBoardUserResponse> {
        return apiService.getLeaderBoard()
    }
}
