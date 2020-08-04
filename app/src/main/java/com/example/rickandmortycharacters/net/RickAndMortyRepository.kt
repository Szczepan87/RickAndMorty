package com.example.rickandmortycharacters.net

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.model.Info
import com.example.rickandmortycharacters.util.ApiResponseWrapper
import com.example.rickandmortycharacters.util.safeApiCall
import kotlinx.coroutines.Dispatchers

class RickAndMortyRepository(private val api: RickAndMortyApi) {

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>>
        get() = _characterList

    private val _info = MutableLiveData<Info>()
    val info: LiveData<Info>
        get() = _info

    private val _repositoryError = MutableLiveData<String?>().apply {
        value = null
    }
    val repositoryError: LiveData<String?>
        get() = _repositoryError

    suspend fun retrieveCharacterPage(pageNo: Int) {
        val response = safeApiCall(Dispatchers.IO) {
            api.getCharactersByPage(pageNo)
        }

        var list: List<Character> = emptyList()

        when (response) {
            is ApiResponseWrapper.Success -> {
                list = response.value.await().body()?.results ?: emptyList()
            }
            is ApiResponseWrapper.GeneralError -> {
                // TODO post an error
            }
            is ApiResponseWrapper.NetworkError -> {
                // TODO post an error
            }
        }
        _characterList.postValue(list)
    }
}