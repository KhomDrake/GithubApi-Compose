package com.vlv.githubapicompose.domain.repository

import com.vlv.githubapicompose.data.GithubApi

class DetailRepository(private val api: GithubApi) {

    suspend fun repositoryDetail(id: Int) = api.repositoryDetail(id)

}