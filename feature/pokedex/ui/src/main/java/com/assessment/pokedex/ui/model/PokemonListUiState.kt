package com.assessment.pokedex.ui.model

import androidx.annotation.StringRes

/**
 * Represents the UI state for the Pokemon list screen.
 * Uses UI models instead of domain models to decouple UI from domain layer.
 */
sealed interface PokemonListUiState {
    data object Loading : PokemonListUiState
    data object Empty : PokemonListUiState

    data class Error(@StringRes val messageResId: Int) : PokemonListUiState
    data class Success(val pokemonList: List<PokemonCharacterUiModel>) : PokemonListUiState
}