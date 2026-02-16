package com.assessment.network

import com.assessment.network.api.PokeAPI
import com.assessment.network.model.ErrorResponse
import com.assessment.network.retrofit.RetrofitProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class PokeApiUnitTest {

    private lateinit var server: MockWebServer
    private lateinit var api: PokeAPI
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()
        api = RetrofitProvider
            .create(
                baseUrl = server.url("/").toString(),
                interceptors = emptyList(),
                moshi = moshi
            )
            .create(PokeAPI::class.java)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test
    fun `species parsing - success returns populated flavor entries`() {
        val body = """
        {
          "capture_rate": 45,
          "base_happiness": 70,
          "flavor_text_entries": [
            {"flavor_text": "A strange seed was planted...\nIt grows with the sun.", "language": {"name": "en", "url": ""}, "version": {"name": "", "url": ""}}
          ],
          "growth_rate": {"name": "medium", "url": ""},
          "hatch_counter": 20,
          "habitat": {"name": "grassland", "url": ""},
          "is_baby": false,
          "is_legendary": false,
          "is_mythical": false,
          "shape": {"name": "quadruped", "url": ""},
          "color": {"name": "green", "url": ""}
        }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        runBlocking {
            when (val result = api.getSpeciesDetails(1)) {
                is com.slack.eithernet.ApiResult.Success -> {
                    val dto = result.value
                    assertTrue(dto.flavorTextEntries.isNotEmpty())
                    assertEquals(1, dto.flavorTextEntries.size)
                }

                else -> fail("Expected success but got $result")
            }
        }
    }

    @Test
    fun `species parsing - missing flavor_text_entries yields empty list`() {
        val body = """
        {
          "capture_rate": 10,
          "base_happiness": 50,
          "growth_rate": {"name": "medium", "url": ""}
        }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        runBlocking {
            when (val result = api.getSpeciesDetails(2)) {
                is com.slack.eithernet.ApiResult.Success -> {
                    val dto = result.value
                    assertNotNull(dto)
                    assertTrue(dto.flavorTextEntries.isEmpty())
                }

                else -> fail("Expected success but got $result")
            }
        }
    }

    @Test
    fun `species parsing - multiple languages parsed correctly`() {
        val body = """
        {
          "flavor_text_entries": [
            {"flavor_text": "Texto en español.", "language": {"name": "es", "url": ""}, "version": {"name": "", "url": ""}},
            {"flavor_text": "English text.", "language": {"name": "en", "url": ""}, "version": {"name": "", "url": ""}}
          ]
        }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        runBlocking {
            when (val result = api.getSpeciesDetails(3)) {
                is com.slack.eithernet.ApiResult.Success -> {
                    val dto = result.value
                    assertEquals(2, dto.flavorTextEntries.size)
                    val langs = dto.flavorTextEntries.map { it.language.name }
                    assertTrue(langs.containsAll(listOf("es", "en")))
                }

                else -> fail("Expected success but got $result")
            }
        }
    }

    @Test
    fun `error body decoded into ErrorResponse on 400`() {
        val errorBody = """
        { "cod": 400, "message": "Bad request", "status": "BAD" }
        """.trimIndent()

        server.enqueue(
            MockResponse().setResponseCode(400).setBody(errorBody)
                .setHeader("Content-Type", "application/json")
        )

        runBlocking {
            when (val result = api.getSpeciesDetails(4)) {
                is com.slack.eithernet.ApiResult.Failure.HttpFailure -> {
                    val err = result.error
                    assertNotNull(err)
                    // ApiResultConverterFactory maps error body into our ErrorResponse model
                    assertTrue(err is ErrorResponse)
                    val er = err as ErrorResponse
                    assertEquals(400, er.code)
                    assertEquals("Bad request", er.message)
                }

                else -> fail("Expected HttpFailure but got $result")
            }
        }
    }

    @Test
    fun `pokemon details parsing - missing stats yields empty list default`() {
        val body = """
        {
          "id": 25,
          "name": "pikachu"
        }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        runBlocking {
            when (val result = api.getPokemonDetails(25)) {
                is com.slack.eithernet.ApiResult.Success -> {
                    val dto = result.value
                    assertNotNull(dto)
                    assertTrue(dto.stats.isEmpty())
                }

                else -> fail("Expected success but got $result")
            }
        }
    }

    @Test
    fun `pokemon details parsing - stats types and sprites parsed`() {
        val body = """
        {
          "id": 1,
          "name": "bulbasaur",
          "height": 7,
          "weight": 69,
          "stats": [
            {"base_stat": 45, "effort": 0, "stat": {"name": "hp", "url": ""}},
            {"base_stat": 49, "effort": 0, "stat": {"name": "attack", "url": ""}}
          ],
          "types": [
            {"slot": 1, "type": {"name": "grass", "url": ""}}
          ],
          "sprites": {"front_default": "https://example.com/bulbasaur.png"}
        }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        runBlocking {
            when (val result = api.getPokemonDetails(1)) {
                is com.slack.eithernet.ApiResult.Success -> {
                    val dto = result.value
                    assertEquals(2, dto.stats.size)
                    assertEquals(45, dto.stats[0].baseStat)
                    assertEquals("hp", dto.stats[0].stat.name)
                    assertEquals(1, dto.types.size)
                    assertEquals("grass", dto.types[0].type.name)
                    assertEquals("https://example.com/bulbasaur.png", dto.sprites.frontDefault)
                }

                else -> fail("Expected success but got $result")
            }
        }
    }

    @Test
    fun `pokemon list parsing - missing results yields empty list`() {
        val body = """
        { "count": 0 }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        runBlocking {
            when (val result = api.getPokemonList()) {
                is com.slack.eithernet.ApiResult.Success -> {
                    val dto = result.value
                    assertEquals(0, dto.count)
                    assertTrue(dto.results.isEmpty())
                }

                else -> fail("Expected success but got $result")
            }
        }
    }

    @Test
    fun `pokemon list parsing - success returns count and results`() {
        val body = """
        {
          "count": 2,
          "results": [
            {"name": "bulbasaur", "url": "https://pokeapi.co/api/v2/pokemon/1/"},
            {"name": "ivysaur", "url": "https://pokeapi.co/api/v2/pokemon/2/"}
          ]
        }
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        runBlocking {
            when (val result = api.getPokemonList()) {
                is com.slack.eithernet.ApiResult.Success -> {
                    val dto = result.value
                    assertEquals(2, dto.count)
                    assertEquals(2, dto.results.size)
                    assertEquals("bulbasaur", dto.results[0].name)
                }

                else -> fail("Expected success but got $result")
            }
        }
    }
}
