package com.example.rickandmortycharacters.vm

import androidx.lifecycle.ViewModel
import com.example.rickandmortycharacters.net.RickAndMortyRepository

class MainActivityViewModel(private val repository: RickAndMortyRepository) : ViewModel() {
}