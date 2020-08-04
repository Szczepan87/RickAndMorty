package com.example.rickandmortycharacters.net

import com.example.rickandmortycharacters.model.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET
    fun getCharactersByPage(@Query("page") page: Int): Deferred<Response<ApiResponse>>
}