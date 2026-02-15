package com.assessment.pokedex.data.contract

import com.assessment.network.api.model.PokemonDetailsResponseDto
import com.assessment.network.api.model.PokemonListResponseDto
import com.assessment.network.api.model.SpeciesDetailsResponseDto
import com.assessment.network.model.ErrorResponse
import com.slack.eithernet.ApiResult

interface RemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): ApiResult<PokemonListResponseDto, ErrorResponse>
    suspend fun getPokemonDetails(id: Int): ApiResult<PokemonDetailsResponseDto, ErrorResponse>
    suspend fun getPokemonSpecies(id: Int): ApiResult<SpeciesDetailsResponseDto, ErrorResponse>
}