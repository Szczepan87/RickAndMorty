package com.example.rickandmortycharacters.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.net.RickAndMortyRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: RickAndMortyRepository) : ViewModel() {

    private val _currentPage = MutableLiveData<Int>(0)

    private val _charactersListOnPage = MutableLiveData<List<Character>>()
    val charactersListOnPage: LiveData<List<Character>>
        get() = _charactersListOnPage

    private val _dataRetrievalError = MutableLiveData<String?>().apply {
        value = null
    }
    val dataRetrievalError: LiveData<String?>
        get() = _dataRetrievalError

    init {
        repository.characterList.observeForever { _charactersListOnPage.postValue(it) }
        repository.repositoryError.observeForever { _dataRetrievalError.postValue(it) }
        downloadNextCharacterPage()
    }

    fun downloadNextCharacterPage() {
        viewModelScope.launch {
            repository.retrieveCharacterPage(_currentPage.value ?: 0)
            _currentPage.postValue(_currentPage.value?.plus(1))
        }
    }
}