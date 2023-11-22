package com.example.housekeeper

import android.app.Application
import com.example.housekeeper.di.dataModule
import com.example.housekeeper.di.interactorModule
import com.example.housekeeper.di.repositoryModule
import com.example.housekeeper.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                viewModelModule,
                interactorModule,
                repositoryModule,
            )
        }
    }
}