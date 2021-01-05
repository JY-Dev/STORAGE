package com.example.storage

import android.app.Application
import com.example.storage.di.networkModule
import com.example.storage.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StorageApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@StorageApplication)
            modules(listOf(roomModule, networkModule))
        }
    }
}