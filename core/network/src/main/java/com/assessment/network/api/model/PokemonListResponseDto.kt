package com.assessment.network.api.model
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class PokemonListResponseDto(
    @field:Json(name = "count")
    val count: Int = 0,
    @field:Json(name = "results")
    val results: List<PokemonCharacterDto> = listOf()
)

@JsonClass(generateAdapter = true)
data class PokemonCharacterDto(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)



