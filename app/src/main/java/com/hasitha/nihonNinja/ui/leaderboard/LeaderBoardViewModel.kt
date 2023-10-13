package com.hasitha.nihonNinja.ui.leaderboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasitha.nihonNinja.model.api.LeaderBoardUserResponse
import com.hasitha.nihonNinja.repository.LeaderBoardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LeaderBoardViewModel @Inject constructor(
    private val repository: LeaderBoardRepository
) : ViewModel() {

    val leaderBoardItems: MutableLiveData<List<LeaderBoardUserResponse>> = MutableLiveData()

    init {
        viewModelScope.launch {
            try {
                leaderBoardItems.value = repository.getLeaderBoard()
            } catch (e: Exception) {
                // Handle exceptions, Ex: network failures here
            }
        }
    }
}
