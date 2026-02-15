package com.assessment.pokedex.data.repository

import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError
import com.assessment.network.api.model.PokemonCharacterDto
import com.assessment.network.api.model.PokemonListResponseDto
import com.assessment.network.model.ErrorResponse
import com.assessment.pokedex.data.contract.RemoteDataSource
import com.assessment.pokedex.domain.contract.RetrievePokemonRepository
import com.slack.eithernet.ApiResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class RetrievePokemonRepositoryImplTest {

    private lateinit var repository: RetrievePokemonRepository
    private lateinit var remoteDataSource: RemoteDataSource
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        remoteDataSource = mockk()
        repository = RetrievePokemonRepositoryImpl(
            remoteDataSource = remoteDataSource,
            ioDispatcher = testDispatcher,
            defaultDispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPokemonList returns Success when remote data source returns success`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val mockPokemonList = listOf(
            PokemonCharacterDto(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
            PokemonCharacterDto(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/"),
            PokemonCharacterDto(name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/")
        )
        val mockResponse = PokemonListResponseDto(count = 3, results = mockPokemonList)

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.success(mockResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Success)
        val successResult = result as BaseResult.Success
        assertEquals(3, successResult.data.size)

        // Verify first pokemon mapping
        assertEquals(1, successResult.data[0].id)
        assertEquals("bulbasaur", successResult.data[0].name)
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            successResult.data[0].imageUrl)

        // Verify second pokemon mapping
        assertEquals(2, successResult.data[1].id.value)
        assertEquals("ivysaur", successResult.data[1].name.value)

        coVerify(exactly = 1) { remoteDataSource.getPokemonList(limit, offset) }
    }

    @Test
    fun `getPokemonList returns Success with empty list when remote returns empty results`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val mockResponse = PokemonListResponseDto(count = 0, results = emptyList())

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.success(mockResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Success)
        val successResult = result as BaseResult.Success
        assertTrue(successResult.data.isEmpty())

        coVerify(exactly = 1) { remoteDataSource.getPokemonList(limit, offset) }
    }

    @Test
    fun `getPokemonList returns GenericError when ApiFailure occurs`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val errorResponse = ErrorResponse(code = 500, message = "Internal server error")

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.apiFailure(errorResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.GenericError)
        assertEquals("Internal server error", (failureResult.error as PokemonError.GenericError).errorMessage)

        coVerify(exactly = 1) { remoteDataSource.getPokemonList(limit, offset) }
    }

    @Test
    fun `getPokemonList returns GenericError when ApiFailure has null error`() = runTest {
        // Given
        val limit = 20
        val offset = 0

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.apiFailure(null)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.GenericError)
        assertEquals("Unknown API error", (failureResult.error as PokemonError.GenericError).errorMessage)
    }

    @Test
    fun `getPokemonList returns BadRequestError when HttpFailure with 400 occurs`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val errorResponse = ErrorResponse(code = 400, message = "Invalid parameters")

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.httpFailure(400, errorResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.BadRequestError)
        assertEquals("Invalid parameters", (failureResult.error as PokemonError.BadRequestError).errorMessage)

        coVerify(exactly = 1) { remoteDataSource.getPokemonList(limit, offset) }
    }

    @Test
    fun `getPokemonList returns UnauthorizedError when HttpFailure with 401 occurs`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val errorResponse = ErrorResponse(code = 401, message = "Unauthorized access")

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.httpFailure(401, errorResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.UnauthorizedError)
        assertEquals("Unauthorized access", (failureResult.error as PokemonError.UnauthorizedError).errorMessage)
    }

    @Test
    fun `getPokemonList returns GenericError when HttpFailure with other status code occurs`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val errorResponse = ErrorResponse(code = 503, message = "Service unavailable")

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.httpFailure(503, errorResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.GenericError)
        assertEquals("Service unavailable", (failureResult.error as PokemonError.GenericError).errorMessage)
    }

    @Test
    fun `getPokemonList returns GenericError when HttpFailure with null error message occurs`() = runTest {
        // Given
        val limit = 20
        val offset = 0

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.httpFailure(404, null)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.GenericError)
        assertEquals("HTTP error: 404", (failureResult.error as PokemonError.GenericError).errorMessage)
    }

    @Test
    fun `getPokemonList returns NetworkError when NetworkFailure occurs`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val networkException = IOException("No internet connection")

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.networkFailure(networkException)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.NetworkError)
        assertEquals("No internet connection", (failureResult.error as PokemonError.NetworkError).errorMessage)

        coVerify(exactly = 1) { remoteDataSource.getPokemonList(limit, offset) }
    }

    @Test
    fun `getPokemonList returns GenericError when UnknownFailure occurs`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val unknownException = Exception("Something went wrong")

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.unknownFailure(unknownException)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Failure)
        val failureResult = result as BaseResult.Failure
        assertTrue(failureResult.error is PokemonError.GenericError)
        assertEquals("Something went wrong", (failureResult.error as PokemonError.GenericError).errorMessage)

        coVerify(exactly = 1) { remoteDataSource.getPokemonList(limit, offset) }
    }

    @Test
    fun `getPokemonList correctly extracts Pokemon ID from URL`() = runTest {
        // Given
        val limit = 20
        val offset = 0
        val mockPokemonList = listOf(
            PokemonCharacterDto(name = "pikachu", url = "https://pokeapi.co/api/v2/pokemon/25/"),
            PokemonCharacterDto(name = "charizard", url = "https://pokeapi.co/api/v2/pokemon/6/")
        )
        val mockResponse = PokemonListResponseDto(count = 2, results = mockPokemonList)

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.success(mockResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Success)
        val successResult = result as BaseResult.Success

        // Verify Pokemon IDs were correctly extracted
        assertEquals(25, successResult.data[0].id.value)
        assertEquals("pikachu", successResult.data[0].name.value)
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            successResult.data[0].imageUrl.value)

        assertEquals(6, successResult.data[1].id.value)
        assertEquals("charizard", successResult.data[1].name.value)
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
            successResult.data[1].imageUrl.value)
    }

    @Test
    fun `getPokemonList handles large offset and limit values`() = runTest {
        // Given
        val limit = 100
        val offset = 500
        val mockResponse = PokemonListResponseDto(count = 0, results = emptyList())

        coEvery {
            remoteDataSource.getPokemonList(limit, offset)
        } returns ApiResult.success(mockResponse)

        // When
        val result = repository.getPokemonList(limit, offset)

        // Then
        assertTrue(result is BaseResult.Success)
        coVerify(exactly = 1) { remoteDataSource.getPokemonList(limit, offset) }
    }
}

