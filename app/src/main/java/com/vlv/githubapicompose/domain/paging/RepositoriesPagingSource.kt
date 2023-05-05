package com.vlv.githubapicompose.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vlv.githubapicompose.data.GithubApi
import com.vlv.githubapicompose.domain.data.Repository

class RepositoriesPagingSource(
    private val githubApi: GithubApi,
    private val initialPage: Int,
    private val itemsPerPage: Int,
    private val language: String
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return runCatching {
            val page = params.key ?: initialPage
            val response = githubApi.repositoryByLanguage(language, page)
            val maxPage = response.totalCount / itemsPerPage
            val nextPage = if(page == maxPage) null else page + 1
            val previousPage = if(page == initialPage) null else page - 1
            val data: List<Repository> = response.items.map(::Repository)
            LoadResult.Page(data, previousPage, nextPage)
        }.getOrElse {
            LoadResult.Error(it)
        }
    }

}