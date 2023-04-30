package com.vlv.githubapicompose.domain.data

import com.vlv.githubapicompose.data.remote.RepositoriesResponse
import com.vlv.githubapicompose.data.remote.RepositoryResponse

data class Repositories(
    val repositories: List<Repository> = listOf()
) {
    constructor(response: RepositoriesResponse) : this(
        response.items.map(::Repository)
    )
}

data class Repository(
    val name: String,
    val authorName: String,
    val stars: Int,
    val url: String
) {
    constructor(response: RepositoryResponse) : this(
        response.name,
        response.owner.login,
        response.stargazersCount,
        response.owner.avatarUrl
    )
}