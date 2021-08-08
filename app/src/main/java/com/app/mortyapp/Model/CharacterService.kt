package com.app.mortyapp.Model

import com.app.mortyapp.CharacterListResponse
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {
    @GET("api/character")
    fun getCharacterList(): Call<CharacterListResponse>

    }
