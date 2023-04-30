package com.vlv.githubapicompose.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlv.githubapicompose.domain.data.Repositories
import com.vlv.githubapicompose.domain.repository.SearchRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SearchRepository) : ViewModel() {

    var state by mutableStateOf(Repositories())
        private set

    fun searchRepository(language: String, page: Int = 1) {
        viewModelScope.launch {
            kotlin.runCatching {
                val response = Repositories(repository.searchRepository(language, page))
                state = state.copy(
                    repositories = response.repositories
                )
            }
        }
    }

}