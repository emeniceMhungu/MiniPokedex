package com.assessment.pokedex.ui.mapper


import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.assessment.common.domain.model.PokemonError
import com.assessment.pokedex.domain.model.PokemonCharacter
import com.assessment.pokedex.domain.model.PokemonCharacterWithDetails
import com.assessment.pokedex.ui.R
import com.assessment.pokedex.ui.model.AboutUiModel
import com.assessment.pokedex.ui.model.BasicInfoNameUiModel
import com.assessment.pokedex.ui.model.BasicInfoValueUiModel
import com.assessment.pokedex.ui.model.DisplayName
import com.assessment.pokedex.ui.model.FormattedId
import com.assessment.pokedex.ui.model.PokemonAbilityUiModel
import com.assessment.pokedex.ui.model.PokemonCharacterUiModel
import com.assessment.pokedex.ui.model.PokemonCharacterWithDetailsUiModel
import com.assessment.pokedex.ui.model.PokemonStatNameUiModel
import com.assessment.pokedex.ui.model.PokemonStatValueUiModel
import com.assessment.pokedex.ui.model.PokemonTypeUiModel
import com.assessment.pokedex.ui.model.PokemonId as UiPokemonId
import com.assessment.pokedex.ui.model.PokemonName as UiPokemonName
import com.assessment.pokedex.ui.model.ImageUrl as UiImageUrl

/**
 * Maps domain Pokemon model to UI model.
 * Transforms domain value classes to UI value classes with additional UI-specific formatting.
 */
fun PokemonCharacterWithDetails.toUiModel(): PokemonCharacterWithDetailsUiModel {
    return PokemonCharacterWithDetailsUiModel(
        id = UiPokemonId(this.id.value),
        name = UiPokemonName(this.name.value),
        displayName = DisplayName(this.name.capitalized()),
        imageUrl = UiImageUrl(this.imageUrl.value),
        formattedId = FormattedId("#${this.id.value.toString().padStart(3, '0')}"),
        about = AboutUiModel(this.about.value),
        basicInfo = this.basicInfo.map {
            BasicInfoNameUiModel(it.first.value) to BasicInfoValueUiModel(
                it.second.value
            )
        },
        stats = this.stats.map {
            PokemonStatNameUiModel(it.first.value) to PokemonStatValueUiModel(
                it.second.value
            )
        },
        abilities = this.abilities.map {
            PokemonAbilityUiModel(it.first.value) to it.second
        },
        types = this.types.map { PokemonTypeUiModel(it.value) },
        pokemonBackgroundColor = mapToColor(this.pokemonColor.value).colorValue,
    )

}

enum class UiPokemonColor(
    val colorValue: Color
) {
    Red(Color.Red),
    Blue(Color.Blue),
    Yellow(Color.Yellow),
    Green(Color.Green),
    Black(Color.Black),
    Brown(Color(0xFF964B00)),
    Purple(Color(0xff800080)),
    Gray(Color.Gray),
    White(Color.White),
    Pink(Color(0xFFFFC0CB)),
    Unknown(Color.Transparent),
}

fun mapToColor(value: String): UiPokemonColor {
    return when {
        value.contentEquals(UiPokemonColor.Red.name, true) -> UiPokemonColor.Red
        value.contentEquals(UiPokemonColor.Blue.name, true) -> UiPokemonColor.Blue
        value.contentEquals(UiPokemonColor.Yellow.name, true) -> UiPokemonColor.Yellow
        value.contentEquals(UiPokemonColor.Green.name, true) -> UiPokemonColor.Green
        value.contentEquals(UiPokemonColor.Black.name, true) -> UiPokemonColor.Black
        value.contentEquals(UiPokemonColor.Brown.name, true) -> UiPokemonColor.Brown
        value.contentEquals(UiPokemonColor.Purple.name, true) -> UiPokemonColor.Purple
        value.contentEquals(UiPokemonColor.Gray.name, true) -> UiPokemonColor.Gray
        value.contentEquals(UiPokemonColor.White.name, true) -> UiPokemonColor.White
        value.contentEquals(UiPokemonColor.Pink.name, true) -> UiPokemonColor.Pink
        else -> UiPokemonColor.Unknown
    }
}

/**
 * Maps domain Pokemon model to UI model.
 * Transforms domain value classes to UI value classes with additional UI-specific formatting.
 */
fun PokemonCharacter.toUiModel(): PokemonCharacterUiModel {
    return PokemonCharacterUiModel(
        id = UiPokemonId(this.id.value),
        name = UiPokemonName(this.name.value),
        displayName = DisplayName(this.name.capitalized()),
        imageUrl = UiImageUrl(this.imageUrl.value),
        formattedId = FormattedId("#${this.id.value.toString().padStart(3, '0')}")
    )
}

/**
 * Extension to map list of domain models to UI models
 */
fun List<PokemonCharacter>.toUiModels(): List<PokemonCharacterUiModel> {
    return this.map { it.toUiModel() }
}

/**
 * Extension to map errorMessage to UI error message
 */
fun PokemonError.mapToErrorMessage(): String {
    return when (this) {
        is PokemonError.BadRequestError -> "Bad request: $errorMessage"
        is PokemonError.UnauthorizedError -> "Unauthorized: $errorMessage"
        is PokemonError.GenericError -> "Generic error: $errorMessage"
        is PokemonError.NetworkError -> "Network error: $errorMessage"
    }
}

/**
 * Maps domain error to UI error message res id for display and to enable localisation.
 */
@StringRes
fun PokemonError.toMessageResId(): Int {
    return when (this) {
        is PokemonError.NetworkError -> R.string.error_network
        is PokemonError.UnauthorizedError -> R.string.error_unauthorized
//        PokemonError.NotFound -> R.string.error_not_found
//        PokemonError.Timeout -> R.string.error_timeout
        is PokemonError.GenericError -> R.string.error_generic
        is PokemonError.BadRequestError -> R.string.error_bad_request

    }
}