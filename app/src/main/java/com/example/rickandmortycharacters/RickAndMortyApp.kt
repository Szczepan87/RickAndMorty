package com.example.rickandmortycharacters

import android.app.Application
import com.example.rickandmortycharacters.di.netModule
import com.example.rickandmortycharacters.di.repositoryModule
import com.example.rickandmortycharacters.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(netModule, repositoryModule, viewModelModule)
        }
    }
}