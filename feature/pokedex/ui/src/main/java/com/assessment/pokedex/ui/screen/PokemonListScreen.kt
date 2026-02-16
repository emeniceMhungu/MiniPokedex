package com.assessment.pokedex.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.assessment.designsystem.component.MiniPokedexAppBackground
import com.assessment.designsystem.component.MiniPokedexAppError
import com.assessment.designsystem.component.MiniPokedexLoader
import com.assessment.designsystem.component.ThemePreviews
import com.assessment.designsystem.theme.MiniPokedexTheme
import com.assessment.pokedex.ui.R
import com.assessment.pokedex.ui.component.MiniPokedexHeader
import com.assessment.pokedex.ui.component.PokemonCard
import com.assessment.pokedex.ui.model.FormattedId
import com.assessment.pokedex.ui.model.PokemonCharacterUiModel
import com.assessment.pokedex.ui.model.PokemonListEvent
import com.assessment.pokedex.ui.model.PokemonListUiState
import com.assessment.pokedex.ui.model.UiDisplayName
import com.assessment.pokedex.ui.model.UiImageUrl
import com.assessment.pokedex.ui.model.UiPokemonId
import com.assessment.pokedex.ui.model.UiPokemonName

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    uiState: PokemonListUiState,
    onEvent: (PokemonListEvent) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        MiniPokedexHeader(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            title = stringResource(R.string.home_title),
        )
        when (uiState) {
            is PokemonListUiState.Empty -> {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_pokemon_found),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            is PokemonListUiState.Error -> {
                MiniPokedexAppError(
                    modifier = Modifier.weight(1f),
                    messageResId = uiState.messageResId,
                    onRetry = { onEvent(PokemonListEvent.Retry) }
                )
            }


            is PokemonListUiState.Loading -> {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    MiniPokedexLoader()
                }
            }

            is PokemonListUiState.Success -> {
                PokemonListContent(
                    modifier = Modifier.weight(1f),
                    pokemonList = uiState.pokemonList,
                    onItemClicked = {
                        onEvent(PokemonListEvent.OnPokemonClicked(id = it))

                    }
                )
            }
        }
    }
}


@Composable
fun PokemonListContent(
    modifier: Modifier = Modifier,
    pokemonList: List<PokemonCharacterUiModel>,
    onItemClicked: (UiPokemonId) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredList = pokemonList.filter {
        it.uiDisplayName.value.contains(searchQuery, ignoreCase = true) ||
                it.formattedId.value.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(stringResource(R.string.search_pokemon_hint)) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = filteredList,
                key = { pokemon -> pokemon.id.value }
            ) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun PokemonListScreenSuccessPreview() {
    val mockPokemonList = listOf(
        PokemonCharacterUiModel(
            id = UiPokemonId(1),
            name = UiPokemonName("bulbasaur"),
            uiDisplayName = UiDisplayName("Bulbasaur"),
            uiImageUrl = UiImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
            formattedId = FormattedId("#001")
        ),
        PokemonCharacterUiModel(
            id = UiPokemonId(4),
            name = UiPokemonName("charmander"),
            uiDisplayName = UiDisplayName("Charmander"),
            uiImageUrl = UiImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
            formattedId = FormattedId("#004")
        )
    )
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            PokemonListScreen(
                uiState = PokemonListUiState.Success(pokemonList = mockPokemonList),
                onEvent = {}
            )
        }
    }

}

@ThemePreviews
@Composable
fun PokemonListScreenErrorPreview() {
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            PokemonListScreen(
                uiState = PokemonListUiState.Error(messageResId = R.string.error_generic),
                onEvent = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun PokemonListScreenLoadingPreview() {
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            PokemonListScreen(
                uiState = PokemonListUiState.Loading,
                onEvent = {}
            )
        }
    }
}
