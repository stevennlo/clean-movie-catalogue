package com.example.moviecatalogue

import android.app.Application
import com.example.core.di.databaseModule
import com.example.core.di.networkModule
import com.example.core.di.repositoryModule
import com.example.moviecatalogue.di.useCaseModule
import com.example.moviecatalogue.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    useCaseModule,
                    networkModule,
                    repositoryModule,
                    databaseModule,
                    viewModelModule
                )
            )
        }
    }
}