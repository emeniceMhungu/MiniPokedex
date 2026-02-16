package com.assessment.common.domain.model

sealed class PokemonError {
    data class GenericError(val errorMessage: String) : PokemonError()
    data class NetworkError(val errorMessage: String) : PokemonError()
    data class BadRequestError(val errorMessage: String) : PokemonError()
    data class UnauthorizedError(val errorMessage: String) : PokemonError()
}