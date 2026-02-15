package com.assessment.pokedex.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.assessment.pokedex.ui.R
import com.assessment.pokedex.ui.component.MiniPokedexHeader
import com.assessment.designsystem.component.MiniPokedexLoader
import com.assessment.pokedex.ui.model.PokemonCharacterUiModel
import com.assessment.pokedex.ui.model.PokemonListEvent
import com.assessment.pokedex.ui.model.PokemonListUiState

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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(uiState.messageResId),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
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

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemon: PokemonCharacterUiModel,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onItemClicked(pokemon.id.value) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imageUrl.value)
                    .crossfade(true)
                    .build(),
                loading = { MiniPokedexLoader() },
                contentDescription = stringResource(R.string.pokemon_image_content_description),
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = pokemon.displayName.value,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}
