package com.vlv.githubapicompose

import android.app.Application
import com.vlv.githubapicompose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class GithubApiCompose : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GithubApiCompose)
            modules(appModule)
        }
    }

}