package com.assessment.pokedex.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.assessment.designsystem.component.MiniPokedexAppBackground
import com.assessment.designsystem.component.MiniPokedexAppError
import com.assessment.designsystem.component.MiniPokedexLoader
import com.assessment.designsystem.component.ThemePreviews
import com.assessment.designsystem.icon.MiniPokedexAppIcons
import com.assessment.designsystem.theme.MiniPokedexTheme
import com.assessment.pokedex.ui.R
import com.assessment.pokedex.ui.component.BasicInfoCard
import com.assessment.pokedex.ui.component.MiniPokedexHeader
import com.assessment.pokedex.ui.model.AboutUiModel
import com.assessment.pokedex.ui.model.BasicInfoNameUiModel
import com.assessment.pokedex.ui.model.BasicInfoValueUiModel
import com.assessment.pokedex.ui.model.DisplayName
import com.assessment.pokedex.ui.model.FormattedId
import com.assessment.pokedex.ui.model.ImageUrl
import com.assessment.pokedex.ui.model.PokemonAbilityUiModel
import com.assessment.pokedex.ui.model.PokemonCharacterWithDetailsUiModel
import com.assessment.pokedex.ui.model.PokemonDetailsUiState
import com.assessment.pokedex.ui.model.PokemonId
import com.assessment.pokedex.ui.model.PokemonName
import com.assessment.pokedex.ui.model.PokemonStatNameUiModel
import com.assessment.pokedex.ui.model.PokemonStatValueUiModel
import com.assessment.pokedex.ui.model.PokemonTypeUiModel

@Composable
fun PokemonCharacterDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: PokemonDetailsUiState,
    onNavigateUp: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is PokemonDetailsUiState.Loading -> {
                MiniPokedexLoader(modifier = modifier)
            }

            is PokemonDetailsUiState.Success -> {
                MiniPokedexHeader(
                    modifier = Modifier.background(uiState.pokemonDetails.pokemonBackgroundColor),
                    title = uiState.pokemonDetails.displayName.value,
                    navIcon = {
                        IconButton(onClick = onNavigateUp) {
                            Icon(
                                MiniPokedexAppIcons.ArrowBack,
                                contentDescription = "onNavigateUp Icon"
                            )
                        }
                    }
                )

                PokemonDetailsContent(
                    pokemonDetails = uiState.pokemonDetails
                )
            }

            is PokemonDetailsUiState.Error -> {
                MiniPokedexAppError(uiState.messageResId)
            }
        }
    }
}

@Composable
fun PokemonDetailsContent(
    modifier: Modifier = Modifier,
    pokemonDetails: PokemonCharacterWithDetailsUiModel
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val imageHeight = this.maxHeight * 0.3f

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(imageHeight))
                Spacer(Modifier.height(16.dp)) // Extra spacing for visual separation.

                if (pokemonDetails.about.value.isNotBlank()) {
                    AboutCard(
                        about = pokemonDetails.about,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                AbilitiesCard(
                    abilities = pokemonDetails.abilities,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                BasicInfoCard(
                    basicInfo = pokemonDetails.basicInfo,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                StatsCard(
                    stats = pokemonDetails.stats,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp)) // Padding at the bottom
            }

            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemonDetails.imageUrl.value)
                    .crossfade(true)
                    .build(),
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        MiniPokedexLoader()
                    }
                },
                contentDescription = stringResource(R.string.pokemon_image_content_description),
                modifier = Modifier
                    .background(pokemonDetails.pokemonBackgroundColor)
                    .fillMaxWidth()
                    .height(imageHeight)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun AboutCard(
    modifier: Modifier = Modifier,
    about: AboutUiModel
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "About",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = about.value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AbilitiesCard(
    modifier: Modifier = Modifier,
    abilities: List<Pair<PokemonAbilityUiModel, Boolean>>
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Abilities",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                abilities.forEach { (ability, isHidden) ->
                    AbilityChip(name = ability.value, isHidden = isHidden)
                }
            }
        }
    }
}

@Composable
fun AbilityChip(
    modifier: Modifier = Modifier,
    name: String,
    isHidden: Boolean
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            if (isHidden) {
                Text(
                    text = "(Hidden)",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun StatsCard(
    modifier: Modifier = Modifier,
    stats: List<Pair<PokemonStatNameUiModel, PokemonStatValueUiModel>>
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Base Stats",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                stats.forEach { (name, value) ->
                    StatRow(name = name.value, value = value.value)
                }
            }
        }
    }
}

@Composable
fun StatRow(
    modifier: Modifier = Modifier,
    name: String,
    value: Int,
    maxValue: Int = 100,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(0.25f)
        )
        LinearProgressIndicator(
            progress = { value.toFloat() / maxValue },
            modifier = Modifier.weight(0.5f),
        )
        Text(
            text = "$value/$maxValue",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(0.25f)
        )
    }
}


@ThemePreviews
@Composable
fun PokemonDetailsScreenSuccessPreview() {
    val mockPokemonDetails = PokemonCharacterWithDetailsUiModel(
        id = PokemonId(25),
        name = PokemonName("pikachu"),
        displayName = DisplayName("Pikachu"),
        imageUrl = ImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"),
        formattedId = FormattedId("#025"),
        about = AboutUiModel("When it is angered, it immediately discharges the energy stored in the pouches on its cheeks."),
        basicInfo = listOf(
            BasicInfoNameUiModel("Height") to BasicInfoValueUiModel("0.4 m"),
            BasicInfoNameUiModel("Weight") to BasicInfoValueUiModel("6.0 kg"),
            BasicInfoNameUiModel("Catch Rate") to BasicInfoValueUiModel("190"),
            BasicInfoNameUiModel("Gender Ratio") to BasicInfoValueUiModel("F: 50% M: 50%")
        ),
        stats = listOf(
            PokemonStatNameUiModel("HP") to PokemonStatValueUiModel(35),
            PokemonStatNameUiModel("Attack") to PokemonStatValueUiModel(55),
            PokemonStatNameUiModel("Defense") to PokemonStatValueUiModel(40),
            PokemonStatNameUiModel("Sp. Atk") to PokemonStatValueUiModel(50),
            PokemonStatNameUiModel("Sp. Def") to PokemonStatValueUiModel(50),
            PokemonStatNameUiModel("Speed") to PokemonStatValueUiModel(90)
        ),
        abilities = listOf(
            PokemonAbilityUiModel("Static") to false,
            PokemonAbilityUiModel("Lightning-rod") to true
        ),
        types = listOf(PokemonTypeUiModel("Electric")),
        pokemonBackgroundColor = Color(0xFFF8D030) // Electric type color
    )

    MiniPokedexTheme {
        MiniPokedexAppBackground {
            PokemonCharacterDetailsScreen(
                uiState = PokemonDetailsUiState.Success(mockPokemonDetails),
                onNavigateUp = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun PokemonDetailsScreenLoadingPreview() {
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            PokemonCharacterDetailsScreen(
                uiState = PokemonDetailsUiState.Loading,
                onNavigateUp = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun PokemonDetailsScreenErrorPreview() {

}
