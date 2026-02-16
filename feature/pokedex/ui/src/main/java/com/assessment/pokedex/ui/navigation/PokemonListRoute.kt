package com.assessment.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assessment.pokedex.ui.model.UiPokemonId
import com.assessment.pokedex.ui.model.PokemonListEvent
import com.assessment.pokedex.ui.screen.PokemonListScreen
import com.assessment.pokedex.ui.viewmodel.RetrievePokemonViewModel

@Composable
fun PokemonListRoute(
    viewModel: RetrievePokemonViewModel = hiltViewModel(),
    onPokemonClick: (UiPokemonId) -> Unit = {}
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onEvent(PokemonListEvent.LoadPokemonList)
    }
    PokemonListScreen(
        uiState = uiState.value,
        onEvent = {
            when (it) {
                is PokemonListEvent.OnPokemonClicked -> {
                    onPokemonClick(it.id)
                }

                else -> viewModel.onEvent(it)
            }
        }
    )
}