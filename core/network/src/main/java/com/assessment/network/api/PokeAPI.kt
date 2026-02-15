package com.assessment.network.api

import com.assessment.network.BuildConfig
import com.assessment.network.api.model.PokemonDetailsResponseDto
import com.assessment.network.api.model.PokemonListResponseDto
import com.assessment.network.api.model.SpeciesDetailsResponseDto
import com.assessment.network.model.ErrorResponse
import com.slack.eithernet.ApiResult
import com.slack.eithernet.DecodeErrorBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {
    companion object {
         private const val API_VERSION = BuildConfig.ApiVersion
    }

    @DecodeErrorBody
    @GET("$API_VERSION/pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): ApiResult<PokemonListResponseDto, ErrorResponse>

    @DecodeErrorBody
    @GET("$API_VERSION/pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") pokemonId: Int
    ): ApiResult<PokemonDetailsResponseDto, ErrorResponse>

    @DecodeErrorBody
    @GET("$API_VERSION/pokemon-species/{id}")
    suspend fun getSpeciesDetails(
        @Path("id") speciesId: Int
    ): ApiResult<SpeciesDetailsResponseDto, ErrorResponse>
}