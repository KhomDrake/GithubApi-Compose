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
    val id: Int,
    val name: String,
    val fullName: String,
    val authorName: String,
    val stars: Int,
    val url: String,
    val description: String,
    val watchers: Int,
    val openIssues: Int,
    val forks: Int
) {
    constructor(response: RepositoryResponse) : this(
        response.id,
        response.name,
        response.fullName,
        response.owner.login,
        response.stargazersCount,
        response.owner.avatarUrl,
        response.description,
        response.watchers,
        response.openIssues,
        response.forks
    )
}