package com.assessment.pokedex.ui.model

/**
 * Value classes for type-safe Pokemon UI properties.
 * These provide compile-time type safety with zero runtime overhead.
 */

@JvmInline
value class PokemonId(val value: Int) {
    init {
        require(value > 0) { "Pokemon ID must be positive, got: $value" }
    }
}

@JvmInline
value class PokemonName(val value: String) {
    init {
        require(value.isNotBlank()) { "Pokemon name cannot be blank" }
    }
}

@JvmInline
value class DisplayName(val value: String) {
    init {
        require(value.isNotBlank()) { "Display name cannot be blank" }
        require(value.first().isUpperCase()) { "Display name must be capitalized" }
    }
}

@JvmInline
value class ImageUrl(val value: String) {
    init {
        require(value.startsWith("http")) { "Image URL must be valid HTTP(S) URL, got: $value" }
    }
}

@JvmInline
value class FormattedId(val value: String) {
    init {
        require(value.matches(Regex("#\\d{3,}"))) { "Formatted ID must match pattern #001, #025, etc." }
    }
}

/**
 * UI representation of a Pokemon character.
 * Uses value classes for type safety and validation.
 */
data class PokemonCharacterUiModel(
    val id: PokemonId,
    val name: PokemonName,
    val displayName: DisplayName,
    val imageUrl: ImageUrl,
    val formattedId: FormattedId
)

