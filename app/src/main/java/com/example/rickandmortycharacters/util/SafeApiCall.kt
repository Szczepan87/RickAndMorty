package com.example.rickandmortycharacters.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ApiResponseWrapper<T> {
    return withContext(dispatcher) {
        try {
            ApiResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    ApiResponseWrapper.NetworkError(throwable.code(), throwable.message)
                }
                else -> {
                    ApiResponseWrapper.GeneralError(throwable.message)
                }
            }
        }
    }
}