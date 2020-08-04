package com.example.rickandmortycharacters.di

import android.content.Context
import com.example.rickandmortycharacters.net.NoInternetConnectionInterceptor
import com.example.rickandmortycharacters.net.RickAndMortyApi
import com.example.rickandmortycharacters.net.RickAndMortyRepository
import com.example.rickandmortycharacters.util.BASE_URL
import com.example.rickandmortycharacters.vm.MainActivityViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    single { provideRickAndMortyApi(androidContext()) }
}

val repositoryModule = module {
    single { RickAndMortyRepository(get()) }
}

val viewModelModule = module {
    single { MainActivityViewModel(get()) }
}

fun provideRickAndMortyApi(context: Context): RickAndMortyApi =
    Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttp3Client(context))
        .baseUrl(BASE_URL)
        .build()
        .create(RickAndMortyApi::class.java)

private fun provideOkHttp3Client(context: Context): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(NoInternetConnectionInterceptor(context))
        .build()