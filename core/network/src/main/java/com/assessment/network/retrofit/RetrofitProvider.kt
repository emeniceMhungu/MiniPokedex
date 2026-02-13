package com.assessment.network.retrofit

import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitProvider {
    fun create(
        baseUrl: String,
        interceptors: List<okhttp3.Interceptor> = emptyList(),
        moshi: Moshi
    ): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        interceptors.forEach { clientBuilder.addInterceptor(it) }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ApiResultConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResultCallAdapterFactory)
            .client(clientBuilder.build())
            .build()
    }
}