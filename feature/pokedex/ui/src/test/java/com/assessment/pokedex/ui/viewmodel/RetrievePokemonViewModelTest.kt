package com.assessment.pokedex.ui.viewmodel

import app.cash.turbine.test
import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.pokedex.domain.model.ImageUrl
import com.assessment.pokedex.domain.model.PokemonCharacter
import com.assessment.pokedex.domain.model.PokemonId
import com.assessment.pokedex.domain.model.PokemonName
import com.assessment.pokedex.domain.usecase.RetrievePokemonListUseCase
import com.assessment.pokedex.ui.model.PokemonListEvent
import com.assessment.pokedex.ui.model.PokemonListUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RetrievePokemonViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var retrievePokemonListUseCase: RetrievePokemonListUseCase
    private lateinit var viewModel: RetrievePokemonViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        retrievePokemonListUseCase = mockk()
        viewModel = RetrievePokemonViewModel(retrievePokemonListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Empty`() = runTest {
        assertEquals(PokemonListUiState.Empty, viewModel.uiState.value)
    }

    @Test
    fun `onEvent LoadPokemonList success`() = runTest {
        val pokemonList = listOf(
            PokemonCharacter(
                name = PokemonName("bulbasaur"),
                imageUrl = ImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"),
                id = PokemonId(25)
            )
        )
        val successResult: BaseResult<List<PokemonCharacter>, PokemonError> =
            BaseResult.Success(pokemonList)

        coEvery { retrievePokemonListUseCase(any(), any()) } returns flowOf(successResult)

        viewModel.onEvent(PokemonListEvent.LoadPokemonList)

        viewModel.uiState.test {
            // Skip initial Empty state
            skipItems(1)
            val successState = awaitItem()
            assertTrue(successState is PokemonListUiState.Success)
            assertEquals(1, (successState as PokemonListUiState.Success).pokemonList.size)
        }
    }

    @Test
    fun `onEvent LoadPokemonList empty`() = runTest {
        val pokemonList = emptyList<PokemonCharacter>()
        val successResult: BaseResult<List<PokemonCharacter>, PokemonError> =
            BaseResult.Success(pokemonList)

        coEvery { retrievePokemonListUseCase(any(), any()) } returns flowOf(successResult)

        viewModel.onEvent(PokemonListEvent.LoadPokemonList)

        viewModel.uiState.test {
            val emptyState = awaitItem()
            assertTrue(emptyState is PokemonListUiState.Empty)
        }
    }

    @Test
    fun `onEvent LoadPokemonList failure`() = runTest {
        val error = PokemonError.GenericError("Test error")
        val failureResult: BaseResult<List<PokemonCharacter>, PokemonError> =
            BaseResult.Failure(error)

        coEvery { retrievePokemonListUseCase(any(), any()) } returns flowOf(failureResult)

        viewModel.onEvent(PokemonListEvent.LoadPokemonList)

        viewModel.uiState.test {
            skipItems(1)
            val errorState = awaitItem()
            assertTrue(errorState is PokemonListUiState.Error)
        }
    }


    @Test
    fun `onEvent Retry success`() = runTest {
        val pokemonList = listOf(
            PokemonCharacter(
                name = PokemonName("bulbasaur"),
                id = PokemonId(25),
                imageUrl = ImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png")
            )
        )
        val successResult: BaseResult<List<PokemonCharacter>, PokemonError> =
            BaseResult.Success(pokemonList)

        coEvery { retrievePokemonListUseCase(any(), any()) } returns flowOf(successResult)

        viewModel.onEvent(PokemonListEvent.Retry)

        viewModel.uiState.test {
            // Skip initial Empty state
            skipItems(1)
            val successState = awaitItem()
            assertTrue(successState is PokemonListUiState.Success)
            assertEquals(1, (successState as PokemonListUiState.Success).pokemonList.size)
        }
    }


}
