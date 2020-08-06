package com.example.rickandmortycharacters.net

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortycharacters.model.Character
import com.example.rickandmortycharacters.model.Info
import com.example.rickandmortycharacters.util.ApiResponseWrapper
import com.example.rickandmortycharacters.util.DEFAULT_INFO
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
        var info: Info = DEFAULT_INFO

        when (response) {
            is ApiResponseWrapper.Success -> {
                list = response.value.await().body()?.results ?: emptyList()
                info = response.value.await().body()?.info ?: DEFAULT_INFO
                _repositoryError.postValue(null)

            }
            is ApiResponseWrapper.GeneralError -> {
                _repositoryError.postValue(response.errorMessage)
            }
            is ApiResponseWrapper.NetworkError -> {
                _repositoryError.postValue(response.errorMessage)
            }
        }
        _characterList.postValue(list)
        _info.postValue(info)
    }
}