package com.assessment.pokedex.data.di

import com.assessment.common.domain.di.DefaultDispatcher
import com.assessment.common.domain.di.IODispatcher
import com.assessment.network.api.PokeAPI
import com.assessment.pokedex.data.contract.RemoteDataSource
import com.assessment.pokedex.data.datasource.RemoteDataSourceImpl
import com.assessment.pokedex.data.repository.RetrievePokemonRepositoryImpl
import com.assessment.pokedex.domain.contract.RetrievePokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokedexDataModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        pokeAPI: PokeAPI
    ): RemoteDataSource = RemoteDataSourceImpl(pokeAPI)

    @Provides
    @Singleton
    fun provideRetrievePokemonRepository(
        remoteDataSource: RemoteDataSource,
        @IODispatcher ioDispatcher: CoroutineDispatcher,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): RetrievePokemonRepository = RetrievePokemonRepositoryImpl(
        remoteDataSource,
        ioDispatcher,
        defaultDispatcher
    )
}
