package com.example.rickandmortycharacters.model


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)