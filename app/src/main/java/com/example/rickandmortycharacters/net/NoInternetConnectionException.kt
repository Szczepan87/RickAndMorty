package com.example.rickandmortycharacters.net

class NoInternetConnectionException : Exception() {
    override val message: String?
        get() = "No Internet connection!"
}