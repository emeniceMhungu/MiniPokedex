package com.assessment.pokedex.data.contract

import com.assessment.network.api.model.PokemonListResponseDto
import com.assessment.network.model.ErrorResponse
import com.slack.eithernet.ApiResult

interface RemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): ApiResult<PokemonListResponseDto, ErrorResponse>
}