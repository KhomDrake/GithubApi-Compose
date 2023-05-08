package com.vlv.githubapicompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.vlv.githubapicompose.domain.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SearchRepository) : ViewModel() {

    private val pageSize = 30

    private var _languageSearched = MutableStateFlow("kotlin")
    val languageSearched = _languageSearched.asStateFlow()

    fun setLanguage(language: String) : Boolean {
        return if(_languageSearched.value != language) {
            _languageSearched.value = language
            true
        } else false
    }

    val repositories = Pager(
        PagingConfig(
            pageSize = pageSize,
            initialLoadSize = 60,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            repository.searchRepositoryPaginated(0, _languageSearched.value, pageSize)
        }
    ).flow.cachedIn(viewModelScope)
}