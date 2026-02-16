package com.assessment.network.api.model
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class PokemonListResponseDto(
    @param:Json(name = "count")
    val count: Int = 0,
    @param:Json(name = "results")
    val results: List<PokemonCharacterDto> = listOf()
)

@JsonClass(generateAdapter = true)
data class PokemonCharacterDto(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)
