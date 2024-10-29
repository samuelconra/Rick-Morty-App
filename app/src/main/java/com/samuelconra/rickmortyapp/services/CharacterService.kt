package com.samuelconra.rickmortyapp.services

import com.samuelconra.rickmortyapp.models.Character
import com.samuelconra.rickmortyapp.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character")
    suspend fun getAllCharacters(): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character
}