package com.assessment.pokedex.domain.contract

import com.assessment.pokedex.domain.model.PokemonDetailsResult

interface GetPokemonDetailsRepository {
    suspend fun getPokemonDetails(id: Int): PokemonDetailsResult
}
