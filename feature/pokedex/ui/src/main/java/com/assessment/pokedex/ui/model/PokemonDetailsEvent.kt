package com.assessment.pokedex.ui.model

sealed interface PokemonDetailsEvent {
    data class LoadPokemonDetails(val id: UiPokemonId) : PokemonDetailsEvent
}
