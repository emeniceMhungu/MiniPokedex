package com.assessment.pokedex.data.repository

import com.assessment.common.domain.di.DefaultDispatcher
import com.assessment.common.domain.di.IODispatcher
import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.network.util.toBaseResult
import com.assessment.pokedex.data.contract.RemoteDataSource
import com.assessment.pokedex.data.mapper.toDomain
import com.assessment.pokedex.domain.contract.GetPokemonDetailsRepository
import com.assessment.pokedex.domain.model.PokemonCharacterWithDetails
import com.slack.eithernet.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : GetPokemonDetailsRepository {
    override suspend fun getPokemonDetails(id: Int): BaseResult<PokemonCharacterWithDetails, PokemonError> {
        val (pokemonDetailsResult, pokemonSpeciesResult) = withContext(ioDispatcher) {
            val deferredDetails = async { remoteDataSource.getPokemonDetails(id) }
            val deferredSpecies = async { remoteDataSource.getPokemonSpecies(id) }
            deferredDetails.await() to deferredSpecies.await()
        }

        return withContext(defaultDispatcher) {
            if (pokemonDetailsResult is ApiResult.Success && pokemonSpeciesResult is ApiResult.Success) {
                BaseResult.Success(
                    pokemonDetailsResult.value.toDomain(pokemonSpeciesResult.value)
                )
            } else {
                if (pokemonDetailsResult is ApiResult.Failure) {
                    return@withContext pokemonDetailsResult.toBaseResult<PokemonCharacterWithDetails>()
                }
                if (pokemonSpeciesResult is ApiResult.Failure) {
                    return@withContext pokemonSpeciesResult.toBaseResult<PokemonCharacterWithDetails>()
                }

                BaseResult.Failure(PokemonError.GenericError("An unknown error occurred"))
            }
        }
    }
}
