package com.example.rickandmortycharacters.model

data class ApiResponse(private val info: Info, private val results: List<Character>) {
}