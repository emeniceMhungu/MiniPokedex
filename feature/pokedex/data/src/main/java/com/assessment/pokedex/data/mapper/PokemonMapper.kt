package com.assessment.pokedex.data.mapper

import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.network.api.model.PokemonCharacterDto
import com.assessment.network.model.ErrorResponse
import com.assessment.pokedex.domain.model.ImageUrl
import com.assessment.pokedex.domain.model.PokemonCharacter
import com.assessment.pokedex.domain.model.PokemonId
import com.assessment.pokedex.domain.model.PokemonName
import com.slack.eithernet.ApiResult
import timber.log.Timber

fun PokemonCharacterDto.toDomain(): PokemonCharacter {
    val id = extractPokemonId(url)
    return PokemonCharacter(
        id = PokemonId(id),
        name = PokemonName(this.name),
        imageUrl = ImageUrl(buildSpriteUrl(id))
    )
}

fun extractPokemonId(url: String): Int {
    val parts = url.split("/")
    return parts[parts.size - 2].toInt()
}

fun buildSpriteUrl(id: Int, isShiny: Boolean = false): String {
    return if (isShiny) "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    else "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}


