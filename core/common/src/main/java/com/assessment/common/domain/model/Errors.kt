package com.assessment.common.domain.model

sealed class Errors {
    data class GenericError(val errorMessage: String) : Errors()
    data class NetworkError(val errorMessage: String) : Errors()
    data class BadRequestError(val errorMessage: String) : Errors()
    data class UnauthorizedError(val errorMessage: String) : Errors()
}