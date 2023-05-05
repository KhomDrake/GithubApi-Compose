package com.vlv.githubapicompose.domain.repository

import com.vlv.githubapicompose.data.GithubApi
import com.vlv.githubapicompose.domain.paging.RepositoriesPagingSource

class SearchRepository(
    private val githubApi: GithubApi
) {

    fun searchRepositoryPaginated(
        initialPage: Int,
        language: String,
        pageSize: Int
    ) = RepositoriesPagingSource(
        githubApi,
        initialPage,
        pageSize,
        "language:$language"
    )

}