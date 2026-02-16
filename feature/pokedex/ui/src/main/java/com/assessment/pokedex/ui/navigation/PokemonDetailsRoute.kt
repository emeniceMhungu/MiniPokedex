package com.assessment.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assessment.pokedex.ui.model.PokemonDetailsEvent
import com.assessment.pokedex.ui.model.UiPokemonId
import com.assessment.pokedex.ui.screen.PokemonCharacterDetailsScreen
import com.assessment.pokedex.ui.viewmodel.GetPokemonDetailsViewModel

@Composable
fun PokemonDetailsRoute(
    pokemonId: UiPokemonId,
    onNavigateUp: () -> Unit,
) {
    val viewModel: GetPokemonDetailsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(pokemonId) {
        viewModel.onEvent(PokemonDetailsEvent.LoadPokemonDetails(pokemonId))
    }

    PokemonCharacterDetailsScreen(
        uiState = uiState.value,
        onNavigateUp = onNavigateUp
    )
}