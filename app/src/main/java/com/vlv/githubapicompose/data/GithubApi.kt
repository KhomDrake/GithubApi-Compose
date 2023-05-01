package com.vlv.githubapicompose.data

import com.vlv.githubapicompose.data.remote.RepositoriesResponse
import com.vlv.githubapicompose.data.remote.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun repositoryByLanguage(
        @Query("q") language: String,
        @Query("page") page: Int = 1,
        @Query("sort") sort: String = "stars"
    ) : RepositoriesResponse

    @GET("repositories/{idRepository}")
    suspend fun repositoryDetail(
        @Path("idRepository") id: Int
    ) : RepositoryResponse

}