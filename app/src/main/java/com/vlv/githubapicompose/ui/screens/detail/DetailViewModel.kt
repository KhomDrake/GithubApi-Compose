package com.vlv.githubapicompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlv.githubapicompose.domain.data.Repository
import com.vlv.githubapicompose.domain.data.Response
import com.vlv.githubapicompose.domain.repository.DetailRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DetailRepository) : ViewModel() {

    val detailData = Response<Repository>()

    fun loadRepositoryData(id: Int) {
        viewModelScope.launch {
            detailData.requestData {
                Repository(repository.repositoryDetail(id))
            }
        }
    }
}