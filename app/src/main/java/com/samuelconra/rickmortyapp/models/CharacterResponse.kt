package com.samuelconra.rickmortyapp.models

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)