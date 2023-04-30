package com.vlv.githubapicompose.domain.repository

import com.vlv.githubapicompose.data.GithubApi
import com.vlv.githubapicompose.data.remote.RepositoriesResponse

class SearchRepository(private val githubApi: GithubApi) {

    suspend fun searchRepository(language: String, page: Int) : RepositoriesResponse {
        return githubApi.repositoryByLanguage("language:$language", page)
    }

}