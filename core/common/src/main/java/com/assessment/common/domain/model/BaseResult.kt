package com.assessment.common.domain.model

sealed class BaseResult<T : Any, E : Any> {

    class Loading<T : Any, E : Any> : BaseResult<T, E>()

    data class Failure<T : Any, E : Any>(val error: E) : BaseResult<T, E>()

    data class Success<T : Any, E : Any>(val data: T) : BaseResult<T, E>()

}