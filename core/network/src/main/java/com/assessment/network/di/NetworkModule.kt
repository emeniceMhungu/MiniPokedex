package com.assessment.network.di


import com.assessment.network.BuildConfig
import com.assessment.network.api.PokeAPI
import com.assessment.network.interceptors.LoggingInterceptor
import com.assessment.network.retrofit.RetrofitProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideInterceptors(): List<Interceptor> = listOf(
        LoggingInterceptor.create()
    )

    @Provides
    @Singleton
    fun provideRetrofit(
        interceptors: @JvmSuppressWildcards List<Interceptor>,
        moshi: Moshi
    ): Retrofit = RetrofitProvider.create(BuildConfig.BASE_URL, interceptors, moshi)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providePokeApi(
        retrofit: Retrofit
    ): PokeAPI = retrofit.create(PokeAPI::class.java)
}
