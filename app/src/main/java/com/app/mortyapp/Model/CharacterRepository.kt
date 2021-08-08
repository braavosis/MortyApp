package com.app.mortyapp.Model

import com.app.mortyapp.Character
import com.app.mortyapp.CharacterRemoteDataSource
import com.app.mortyapp.NetworkResponse

class CharacterRepository constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) {
    fun getCharacterList(networkResponse: NetworkResponse<List<Character>>){
        return remoteDataSource.getCharacterList(networkResponse)
    }
}