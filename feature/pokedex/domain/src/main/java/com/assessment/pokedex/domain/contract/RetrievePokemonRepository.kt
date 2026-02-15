package com.assessment.pokedex.domain.contract

import com.assessment.pokedex.domain.model.PokemonListResult

interface RetrievePokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResult
}