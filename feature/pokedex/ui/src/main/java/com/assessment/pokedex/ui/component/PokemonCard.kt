package com.assessment.pokedex.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import com.assessment.designsystem.component.MiniPokedexAppBackground
import com.assessment.designsystem.component.MiniPokedexLoader
import com.assessment.designsystem.component.ThemePreviews
import com.assessment.designsystem.theme.MiniPokedexTheme
import com.assessment.pokedex.ui.R
import com.assessment.pokedex.ui.model.DisplayName
import com.assessment.pokedex.ui.model.FormattedId
import com.assessment.pokedex.ui.model.ImageUrl
import com.assessment.pokedex.ui.model.PokemonCharacterUiModel
import com.assessment.pokedex.ui.model.PokemonId
import com.assessment.pokedex.ui.model.PokemonName

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

@ThemePreviews
@Composable
fun PokemonCardDefaultPreview() {
    MiniPokedexTheme {
        MiniPokedexAppBackground(modifier = Modifier.height(200.dp)) {
            PokemonCard(
                pokemon = PokemonCharacterUiModel(
                    id = PokemonId(1),
                    name = PokemonName("bulbasaur"),
                    displayName = DisplayName("Bulbasaur"),
                    imageUrl = ImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                    formattedId = FormattedId("#001")
                ),
                onItemClicked = {}
            )
        }
    }
}
