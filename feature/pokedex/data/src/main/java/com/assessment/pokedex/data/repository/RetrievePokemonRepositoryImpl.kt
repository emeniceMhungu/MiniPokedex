package com.assessment.pokedex.data.repository

import com.assessment.common.domain.di.DefaultDispatcher
import com.assessment.common.domain.di.IODispatcher
import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.network.api.model.PokemonListResponseDto
import com.assessment.network.model.ErrorResponse
import com.assessment.pokedex.data.contract.RemoteDataSource
import com.assessment.pokedex.data.mapper.mapErrorToBaseResult
import com.assessment.pokedex.data.mapper.toDomain
import com.assessment.pokedex.domain.contract.RetrievePokemonRepository
import com.assessment.pokedex.domain.model.PokemonCharacter
import com.slack.eithernet.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RetrievePokemonRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : RetrievePokemonRepository {
    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): BaseResult<List<PokemonCharacter>, PokemonError> {
        val result: ApiResult<PokemonListResponseDto, ErrorResponse> =
            withContext(ioDispatcher){
                remoteDataSource.getPokemonList(limit, offset)
            }


        return withContext(defaultDispatcher) {
            when (result) {
                is ApiResult.Success -> {
                    BaseResult.Success(result.value.results.map { it.toDomain() })
                }

                is ApiResult.Failure -> {
                    result.mapErrorToBaseResult()
                }
            }
        }
    }
}