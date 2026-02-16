package com.assessment.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @param:Json(name = "cod")
    val code: Int? = null,

    @param:Json(name = "message")
    val message: String? = null,

    @param:Json(name = "status")
    val status: String? = null,

    @param:Json(name = "error")
    val error: String? = null
)