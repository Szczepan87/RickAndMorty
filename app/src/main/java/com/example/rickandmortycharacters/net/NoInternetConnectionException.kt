package com.example.rickandmortycharacters.net

import java.io.IOException

class NoInternetConnectionException : IOException() {
    override val message: String?
        get() = "No Internet connection!"
}