package com.example.rickandmortycharacters.di

import com.example.rickandmortycharacters.net.RickAndMortyApi
import com.example.rickandmortycharacters.net.RickAndMortyRepository
import com.example.rickandmortycharacters.util.BASE_URL
import com.example.rickandmortycharacters.vm.MainActivityViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    provideRickAndMortyApi()
}

val repositoryModule = module {
    single { RickAndMortyRepository(get()) }
}

val viewModelModule = module {
    single { MainActivityViewModel(get()) }
}

fun provideRickAndMortyApi(): RickAndMortyApi =
    Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .baseUrl(BASE_URL)
        .build()
        .create(RickAndMortyApi::class.java)
