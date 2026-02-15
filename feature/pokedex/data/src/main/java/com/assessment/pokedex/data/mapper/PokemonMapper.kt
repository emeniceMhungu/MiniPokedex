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

fun buildSpriteUrl(id: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

fun ApiResult.Failure<ErrorResponse>.mapErrorToBaseResult(): BaseResult<List<PokemonCharacter>, PokemonError> {
    return when (this) {
        is ApiResult.Failure.ApiFailure -> {
            Timber.d("ApiFailure: ${this.error?.message}")
            BaseResult.Failure(
                PokemonError.GenericError(
                    this.error?.message ?: "Unknown API error"
                )
            )
        }
        is ApiResult.Failure.HttpFailure -> {
            Timber.d("HttpFailure: ${this.error?.message}")
            BaseResult.Failure(
                when (this.code) {
                    400 -> PokemonError.BadRequestError(this.error?.message ?: "Bad request")
                    401 -> PokemonError.UnauthorizedError(this.error?.message ?: "Unauthorized")
                    else -> PokemonError.GenericError(this.error?.message ?: "HTTP error: ${this.code}")
                }
            )
        }
        is ApiResult.Failure.NetworkFailure -> {
            Timber.d("NetworkFailure: ${this.error.message}")
            BaseResult.Failure(
                PokemonError.NetworkError(this.error.message ?: "Network error")
            )
        }
        is ApiResult.Failure.UnknownFailure -> {
            Timber.d("UnknownFailure: ${this.error.message}")
            BaseResult.Failure(
                PokemonError.GenericError(this.error.message ?: "Unknown failure")
            )
        }
    }
}


