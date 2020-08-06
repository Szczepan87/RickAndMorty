package com.example.rickandmortycharacters.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.net.RickAndMortyRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: RickAndMortyRepository) : ViewModel() {

    private val _currentPage = MutableLiveData<Int>(0)
    val currentPage: LiveData<Int>
        get() = _currentPage

    private val _charactersListOnPage = MutableLiveData<List<Character>>()
    val charactersListOnPage: LiveData<List<Character>>
        get() = _charactersListOnPage

    // TODO save recycler position
    private val _dataRetrievalError = MutableLiveData<String?>().apply {
        value = null
    }
    val dataRetrievalError: LiveData<String?>
        get() = _dataRetrievalError

    init {
        repository.characterList.observeForever { _charactersListOnPage.postValue(it) }
        downloadNextCharacterPage()
    }

    fun downloadNextCharacterPage() {
        viewModelScope.launch {
            Log.d("VIEW MODEL", "Page to download: ${currentPage.value}")
            repository.retrieveCharacterPage(currentPage.value ?: 0)
            _currentPage.postValue(currentPage.value?.plus(1))
        }
    }
}