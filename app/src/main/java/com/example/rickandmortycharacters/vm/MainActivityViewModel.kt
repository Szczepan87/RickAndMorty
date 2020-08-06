package com.example.rickandmortycharacters.vm

import androidx.lifecycle.*
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.model.Info
import com.example.rickandmortycharacters.net.RickAndMortyRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: RickAndMortyRepository) : ViewModel() {

    private val _currentPage = MutableLiveData<Int>(0)
    val currentPage: LiveData<Int>
        get() = _currentPage

    private val _charactersListOnPage = MutableLiveData<List<Character>>()
    val charactersListOnPage: LiveData<List<Character>>
        get() = _charactersListOnPage

    private val _downloadedCharactersList = MutableLiveData<List<Character>>()
    val downloadedCharactersList: LiveData<List<Character>>
        get() = _downloadedCharactersList

    private val _dataRetrievalError = MutableLiveData<String?>().apply {
        value = null
    }
    val dataRetrievalError: LiveData<String?>
        get() = _dataRetrievalError

    private val newCharactersListObserver =
        Observer<List<Character>> { _charactersListOnPage.postValue(it) }
    private val newPageObserver =
        Observer<Info> { _currentPage.postValue(currentPage.value?.plus(1)) }

    init {
        repository.characterList.observeForever { newCharactersListObserver }
        repository.info.observeForever { newPageObserver }
    }

    fun downloadNextCharacterPage() {
        viewModelScope.launch { repository.retrieveCharacterPage(currentPage.value ?: 0) }
    }

    override fun onCleared() {
        repository.characterList.removeObserver(newCharactersListObserver)
        repository.info.observeForever(newPageObserver)

        super.onCleared()
    }
}