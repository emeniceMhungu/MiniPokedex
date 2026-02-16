package com.assessment.pokedex.domain.usecase

import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.pokedex.domain.contract.GetPokemonDetailsRepository
import com.assessment.pokedex.domain.model.PokemonDetailsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val getPokemonDetailsRepository: GetPokemonDetailsRepository
){
    operator fun invoke(id: Int): Flow<PokemonDetailsResult> = flow {
        emit(BaseResult.Loading())
        emit(getPokemonDetailsRepository.getPokemonDetails(id))
    }.catch { e ->
        emit(BaseResult.Failure(PokemonError.GenericError(e.message ?: "Unknown failure")))
    }
}