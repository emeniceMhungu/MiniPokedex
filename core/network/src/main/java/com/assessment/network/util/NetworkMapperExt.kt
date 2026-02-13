package com.assessment.network.util

import com.assessment.network.model.ErrorResponse
import com.assessment.common.domain.model.BaseResult
import com.slack.eithernet.ApiResult
import timber.log.Timber

fun ApiResult.Failure<ErrorResponse>.toBaseResult(): BaseResult<Unit, ErrorResponse> {
    when (this) {
        is ApiResult.Failure.ApiFailure -> {
            Timber.d("ApiFailure: ${this.error?.message}")
            return BaseResult.Failure(
                this.error ?: ErrorResponse(
                    0,
                    "Unknown error"
                )
            )
        }

        is ApiResult.Failure.HttpFailure -> {
            Timber.d("HttpFailure: ${this.error?.message}")
            return BaseResult.Failure(
                ErrorResponse(
                    this.code,
                    this.error?.message
                )
            )
        }

        is ApiResult.Failure.NetworkFailure -> {
            Timber.d("NetworkFailure: ${this.error.message}")
            return BaseResult.Failure(
                ErrorResponse(
                    0,
                    "Network error"
                )
            )
        }

        is ApiResult.Failure.UnknownFailure -> {
            Timber.d("UnknownFailure: ${this.error.message}")
            return BaseResult.Failure(
                ErrorResponse(
                    0,
                    "Unknown failure"
                )
            )
        }
    }
}