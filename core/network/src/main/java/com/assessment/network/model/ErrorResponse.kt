package com.assessment.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @field:Json(name = "cod")
    val code: Int? = null,

    @field:Json(name = "message")
    val message: String? = null,

    @field:Json(name = "status")
    val status: String? = null,

    @field:Json(name = "error")
    val error: String? = null
)