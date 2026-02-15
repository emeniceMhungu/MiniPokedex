package com.assessment.network.util

import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.network.model.ErrorResponse
import com.slack.eithernet.ApiResult
import timber.log.Timber

fun <T : Any> ApiResult.Failure<ErrorResponse>.toBaseResult(): BaseResult<T, PokemonError> {
    return when (this) {
        is ApiResult.Failure.ApiFailure -> {
            val errorMessage = this.error?.message ?: "An API error occurred"
            Timber.d("ApiFailure: $errorMessage")
            BaseResult.Failure(PokemonError.GenericError(errorMessage))
        }

        is ApiResult.Failure.HttpFailure -> {
            val errorMessage = this.error?.message ?: "An HTTP error occurred"
            Timber.d("HttpFailure: code=${this.code}, message=$errorMessage")
            when (this.code) {
                400 -> BaseResult.Failure(PokemonError.BadRequestError(errorMessage))
                401 -> BaseResult.Failure(PokemonError.UnauthorizedError(errorMessage))
                else -> BaseResult.Failure(PokemonError.GenericError(errorMessage))
            }
        }

        is ApiResult.Failure.NetworkFailure -> {
            val errorMessage = this.error.message ?: "A network error occurred"
            Timber.d("NetworkFailure: $errorMessage")
            BaseResult.Failure(PokemonError.NetworkError(errorMessage))
        }

        is ApiResult.Failure.UnknownFailure -> {
            val errorMessage = this.error.message ?: "An unknown error occurred"
            Timber.d("UnknownFailure: $errorMessage")
            BaseResult.Failure(PokemonError.GenericError(errorMessage))
        }
    }
}
