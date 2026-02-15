package com.assessment.pokedex.ui.model

import androidx.annotation.StringRes

sealed interface PokemonDetailsUiState {
    object Loading : PokemonDetailsUiState
    data class Error(@StringRes val messageResId: Int) : PokemonDetailsUiState
    data class Success(val pokemonDetails: PokemonCharacterWithDetailsUiModel) : PokemonDetailsUiState
}
