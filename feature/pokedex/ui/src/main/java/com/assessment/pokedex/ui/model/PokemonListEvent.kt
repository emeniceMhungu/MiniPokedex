package com.assessment.pokedex.ui.model

sealed interface PokemonListEvent {
    data object LoadPokemonList: PokemonListEvent
    data class OnPokemonClicked(val id: UiPokemonId): PokemonListEvent
    data object Retry: PokemonListEvent
}
