package com.vlv.githubapicompose.di

import com.vlv.githubapicompose.data.GithubApiRetrofit
import com.vlv.githubapicompose.domain.repository.DetailRepository
import com.vlv.githubapicompose.domain.repository.SearchRepository
import com.vlv.githubapicompose.ui.screens.detail.DetailViewModel
import com.vlv.githubapicompose.ui.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        GithubApiRetrofit.githubApiService()
    }
    single {
        SearchRepository(get())
    }
    single {
        DetailRepository(get())
    }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}