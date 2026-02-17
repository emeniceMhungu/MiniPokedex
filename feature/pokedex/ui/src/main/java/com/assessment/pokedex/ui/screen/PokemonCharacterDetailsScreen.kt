package com.assessment.pokedex.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.assessment.pokedex.ui.component.AbilitiesCard
import com.assessment.pokedex.ui.component.AboutCard
import com.assessment.pokedex.ui.component.BasicInfoCard
import com.assessment.pokedex.ui.component.MiniPokedexHeader
import com.assessment.pokedex.ui.component.StatsCard
import com.assessment.pokedex.ui.model.AboutUiModel
import com.assessment.pokedex.ui.model.BasicInfoNameUiModel
import com.assessment.pokedex.ui.model.BasicInfoValueUiModel
import com.assessment.pokedex.ui.model.UiDisplayName
import com.assessment.pokedex.ui.model.FormattedId
import com.assessment.pokedex.ui.model.UiImageUrl
import com.assessment.pokedex.ui.model.PokemonAbilityUiModel
import com.assessment.pokedex.ui.model.PokemonCharacterWithDetailsUiModel
import com.assessment.pokedex.ui.model.PokemonDetailsUiState
import com.assessment.pokedex.ui.model.UiPokemonId
import com.assessment.pokedex.ui.model.UiPokemonName
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
                    title = uiState.pokemonDetails.uiDisplayName.value,
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
                MiniPokedexAppError(
                    messageResId = uiState.messageResId,
                    onRetry = {
                        onNavigateUp()
                    }
                )
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
                    .data(pokemonDetails.uiImageUrl.value)
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


@ThemePreviews
@Composable
fun PokemonDetailsScreenSuccessPreview() {
    val mockPokemonDetails = PokemonCharacterWithDetailsUiModel(
        id = UiPokemonId(25),
        name = UiPokemonName("pikachu"),
        uiDisplayName = UiDisplayName("Pikachu"),
        uiImageUrl = UiImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"),
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
