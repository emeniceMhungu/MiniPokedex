package com.assessment.pokedex.ui.model

sealed interface PokemonListEvent {
    data object LoadPokemonList: PokemonListEvent

}
