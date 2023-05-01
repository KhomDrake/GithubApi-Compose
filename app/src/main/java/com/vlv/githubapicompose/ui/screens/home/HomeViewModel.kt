package com.vlv.githubapicompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlv.githubapicompose.domain.data.Repositories
import com.vlv.githubapicompose.domain.data.Repository
import com.vlv.githubapicompose.domain.data.Response
import com.vlv.githubapicompose.domain.repository.SearchRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SearchRepository) : ViewModel() {

    val state: Response<List<Repository>> = Response()

    fun searchRepository(language: String, page: Int = 1) {
        viewModelScope.launch {
            state.requestData {
                Repositories(repository.searchRepository(language, page)).repositories
            }
        }
    }

}