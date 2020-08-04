package com.example.rickandmortycharacters

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RickAndMortyApp)
            modules()
        }
    }
}