package com.assessment.pokedex.domain.model


@JvmInline
value class PokemonId(val value: Int) {
    init {
        require(value > 0) { "Pokemon ID must be positive" }
    }
}

@JvmInline
value class PokemonName(val value: String) {
    init {
        require(value.isNotBlank()) { "Pokemon name cannot be blank" }
    }

    fun capitalized() = value.replaceFirstChar { it.uppercase() }
}

@JvmInline
value class ImageUrl(val value: String) {
    init {
        require(value.startsWith("https")) { "Image URL must be valid" }
    }
}

data class PokemonCharacter(
    val id: PokemonId,
    val name: PokemonName,
    val imageUrl: ImageUrl
)
