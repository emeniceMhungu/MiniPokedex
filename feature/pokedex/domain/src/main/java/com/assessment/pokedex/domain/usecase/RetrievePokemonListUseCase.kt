package com.assessment.pokedex.domain.usecase

import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.pokedex.domain.contract.RetrievePokemonRepository
import com.assessment.pokedex.domain.model.PokemonListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RetrievePokemonListUseCase @Inject constructor(
    private val retrievePokemonRepository: RetrievePokemonRepository,
) {
    suspend operator fun invoke(
        limit: Int,
        offset: Int
    ): Flow<PokemonListResult> = flow {
        emit(BaseResult.Loading())
        emit(retrievePokemonRepository.getPokemonList(limit, offset))
    }.catch { e ->
        emit(BaseResult.Failure(PokemonError.GenericError(e.message ?: "Unknown failure")))
    }
}

