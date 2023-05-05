package com.vlv.githubapicompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.vlv.githubapicompose.domain.repository.SearchRepository

class HomeViewModel(private val repository: SearchRepository) : ViewModel() {

    private var language = "kotlin"
    private val pageSize = 30

    fun setLanguage(language: String) {
        this.language = language
    }

    val repositories = Pager(
        PagingConfig(
            pageSize = pageSize,
            initialLoadSize = 60,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            repository.searchRepositoryPaginated(0, language, pageSize)
        }
    ).flow.cachedIn(viewModelScope)
}