package com.assessment.network.interceptors

import com.assessment.network.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {
    fun create(isDebug: Boolean = BuildConfig.DEBUG): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}
