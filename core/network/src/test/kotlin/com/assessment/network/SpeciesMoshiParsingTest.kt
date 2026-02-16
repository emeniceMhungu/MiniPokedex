package com.assessment.network

import com.assessment.network.api.model.SpeciesDetailsResponseDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert.assertTrue
import org.junit.Test

class SpeciesMoshiParsingTest {
    @Test
    fun `moshi parses flavor_text_entries`() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val json = """
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

        val adapter = moshi.adapter(SpeciesDetailsResponseDto::class.java)
        println("Adapter class: ${adapter::class.java.name}")

        // reflect into NullSafeJsonAdapter to see the delegate
        try {
            val delegateField = adapter::class.java.getDeclaredField("delegate")
            delegateField.isAccessible = true
            val delegate = delegateField.get(adapter)
            println("Delegate class: ${delegate::class.java.name}")
        } catch (e: Exception) {
            println("Could not access delegate: ${e.message}")
        }

        println("KotlinJsonAdapterFactory class: ${KotlinJsonAdapterFactory::class.java.name}")

        val dto = adapter.fromJson(json)!!

        val producedJson = adapter.toJson(dto)
        println("Adapter produced JSON: $producedJson")
        println("Parsed flavor entries (size): ${dto.flavorTextEntries.size}")
        assertTrue(dto.flavorTextEntries.isNotEmpty())
    }
}
