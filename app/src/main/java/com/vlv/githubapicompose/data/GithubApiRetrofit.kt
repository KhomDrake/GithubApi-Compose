package com.vlv.githubapicompose.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GithubApiRetrofit {

    fun githubApiService() : GithubApi {
        return retrofit().create(GithubApi::class.java)
    }

    private fun retrofit() = run {
        Retrofit.Builder().apply {
            baseUrl("https://api.github.com/")
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp())
        }.build()
    }

    private fun okHttp() : OkHttpClient {
        return OkHttpClient().newBuilder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
            }.build()
    }

}