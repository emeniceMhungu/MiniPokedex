package com.assessment.pokedex.data.datasource

import com.assessment.network.api.PokeAPI
import com.assessment.network.api.model.PokemonListResponseDto
import com.assessment.network.model.ErrorResponse
import com.assessment.pokedex.data.contract.RemoteDataSource
import com.slack.eithernet.ApiResult
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val pokeAPI: PokeAPI
) : RemoteDataSource {
    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): ApiResult<PokemonListResponseDto, ErrorResponse> {
        return pokeAPI.getPokemonList(limit, offset)
    }
}