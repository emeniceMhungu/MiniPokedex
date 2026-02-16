package com.assessment.pokedex.ui.model

import androidx.compose.ui.graphics.Color

@JvmInline value class AboutUiModel(val value: String)
@JvmInline value class BasicInfoNameUiModel(val value: String)

@JvmInline value class BasicInfoValueUiModel(val value: String)

@JvmInline value class PokemonStatNameUiModel(val value: String)

@JvmInline value class PokemonStatValueUiModel(val value: Int)

@JvmInline value class PokemonAbilityUiModel(val value: String)

@JvmInline value class PokemonTypeUiModel(val value: String)





data class PokemonCharacterWithDetailsUiModel(
    val id: UiPokemonId,
    val name: UiPokemonName,
    val uiDisplayName: UiDisplayName,
    val uiImageUrl: UiImageUrl,
    val formattedId: FormattedId,
    val about: AboutUiModel,
    val basicInfo: List<Pair<BasicInfoNameUiModel, BasicInfoValueUiModel>>,
    val stats: List<Pair<PokemonStatNameUiModel, PokemonStatValueUiModel>>,
    val abilities: List<Pair<PokemonAbilityUiModel, Boolean>>,
    val types: List<PokemonTypeUiModel>,
    val pokemonBackgroundColor: Color,
)
