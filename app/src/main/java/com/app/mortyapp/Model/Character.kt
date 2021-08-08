package com.app.mortyapp

import com.google.gson.annotations.SerializedName

data class Character (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String
)

data class CharacterListResponse(
    @SerializedName("results") val results: List<Character>
)