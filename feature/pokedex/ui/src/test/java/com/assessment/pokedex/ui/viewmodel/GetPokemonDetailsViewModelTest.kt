package com.assessment.pokedex.ui.viewmodel

import app.cash.turbine.test
import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.pokedex.domain.model.About
import com.assessment.pokedex.domain.model.ImageUrl
import com.assessment.pokedex.domain.model.PokemonCharacterWithDetails
import com.assessment.pokedex.domain.model.PokemonColor
import com.assessment.pokedex.domain.model.PokemonId
import com.assessment.pokedex.domain.model.PokemonName
import com.assessment.pokedex.domain.usecase.GetPokemonDetailsUseCase
import com.assessment.pokedex.ui.model.PokemonDetailsEvent
import com.assessment.pokedex.ui.model.PokemonDetailsUiState
import com.assessment.pokedex.ui.model.UiPokemonId
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
class GetPokemonDetailsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getPokemonDetailsUseCase: GetPokemonDetailsUseCase
    private lateinit var viewModel: GetPokemonDetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPokemonDetailsUseCase = mockk()
        viewModel = GetPokemonDetailsViewModel(getPokemonDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Loading`() = runTest {
        assertEquals(PokemonDetailsUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `onEvent LoadPokemonDetails success`() = runTest {
        //Arrange
        val pokemonId = UiPokemonId(1)
        val pokemonDetails = PokemonCharacterWithDetails(
            id = PokemonId(1),
            name = PokemonName("bulbasaur"),
            basicInfo = emptyList(),
            types = emptyList(),
            stats = emptyList(),
            abilities = emptyList(),
            about = About("Test about"),
            imageUrl = ImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
            pokemonColor = PokemonColor("yellow")
        )
        val successResult: BaseResult<PokemonCharacterWithDetails, PokemonError> = BaseResult.Success(pokemonDetails)

        coEvery { getPokemonDetailsUseCase(pokemonId.value) } returns flowOf(successResult)

        viewModel.onEvent(PokemonDetailsEvent.LoadPokemonDetails(pokemonId))

        viewModel.uiState.test {
            // Skip initial Loading state
            skipItems(1)
            val successState = awaitItem()
            assertTrue(successState is PokemonDetailsUiState.Success)
            assertEquals("bulbasaur", (successState as PokemonDetailsUiState.Success).pokemonDetails.name.value)
        }
    }

    @Test
    fun `onEvent LoadPokemonDetails failure`() = runTest {
        val pokemonId = UiPokemonId(1)
        val error = PokemonError.GenericError("Test error")
        val failureResult: BaseResult<PokemonCharacterWithDetails, PokemonError> = BaseResult.Failure(error)

        coEvery { getPokemonDetailsUseCase(pokemonId.value) } returns flowOf(failureResult)

        viewModel.onEvent(PokemonDetailsEvent.LoadPokemonDetails(pokemonId))

        viewModel.uiState.test {
            // Skip initial Loading state
            skipItems(1)
            val errorState = awaitItem()
            assertTrue(errorState is PokemonDetailsUiState.Error)
        }
    }

}
