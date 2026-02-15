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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.assessment.pokedex.ui.model.DisplayName
import com.assessment.pokedex.ui.model.FormattedId
import com.assessment.pokedex.ui.model.ImageUrl
import com.assessment.pokedex.ui.model.PokemonCharacterUiModel
import com.assessment.pokedex.ui.model.PokemonId
import com.assessment.pokedex.ui.model.PokemonListEvent
import com.assessment.pokedex.ui.model.PokemonListUiState
import com.assessment.pokedex.ui.model.PokemonName

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
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_pokemon_found),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            is PokemonListUiState.Error -> {
                MiniPokedexAppError(uiState.messageResId)
            }


            is PokemonListUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    MiniPokedexLoader()
                }
            }

            is PokemonListUiState.Success -> {
                PokemonListContent(
                    pokemonList = uiState.pokemonList,
                    onItemClicked = {
                        // TODO: Handle item click
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
    onItemClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.search_pokemon_hint)) },
            singleLine = true
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = pokemonList,
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
            id = PokemonId(1),
            name = PokemonName("bulbasaur"),
            displayName = DisplayName("Bulbasaur"),
            imageUrl = ImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
            formattedId = FormattedId("#001")
        ),
        PokemonCharacterUiModel(
            id = PokemonId(4),
            name = PokemonName("charmander"),
            displayName = DisplayName("Charmander"),
            imageUrl = ImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
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
