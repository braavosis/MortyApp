package com.app.mortyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mortyapp.Model.CharacterRepository

class MainViewModel constructor(
    private val repository: CharacterRepository
): ViewModel() {

    private var _characterList = MutableLiveData<Resource<List<Character>>>()
    val characterList: LiveData<Resource<List<Character>>>
        get() = _characterList

    init{
        getCharacterList()
    }

    public fun getCharacterList(){
        _characterList.value = Resource(NetworkStatus.LOADING)
        val response = object: NetworkResponse<List<Character>>{
            override fun onResponse(value: Resource<List<Character>>) {
                _characterList.value = value
            }
        }

        repository.getCharacterList(response)
    }

}