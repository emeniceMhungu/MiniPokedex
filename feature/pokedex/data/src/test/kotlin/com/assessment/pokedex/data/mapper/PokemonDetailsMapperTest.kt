package com.assessment.pokedex.data.mapper

import com.assessment.network.api.model.*
import com.assessment.network.api.model.Color
import com.assessment.network.api.model.Habitat
import com.assessment.network.api.model.Shape
import com.assessment.network.api.model.FlavorTextEntry
import com.assessment.network.api.model.Language
import com.assessment.network.api.model.Version
import com.assessment.network.api.model.StatXX
import com.assessment.network.api.model.StatX
import com.assessment.network.api.model.Ability
import com.assessment.network.api.model.AbilityX
import com.assessment.network.api.model.Type
import com.assessment.network.api.model.TypeX
import com.assessment.network.api.model.GrowthRate
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonDetailsMapperTest {

    @Test
    fun `toDomain maps stats and flavor text correctly`() {
        val pokemonDto = PokemonDetailsResponseDto(
            id = 1,
            name = "bulbasaur",
            height = 7,
            weight = 69,
            baseExperience = 64,
            stats = listOf(
                StatXX(baseStat = 45, effort = 0, stat = StatX(name = "hp")),
                StatXX(baseStat = 49, effort = 0, stat = StatX(name = "attack"))
            ),
            abilities = listOf(
                Ability(
                    ability = AbilityX(name = "overgrow"),
                    isHidden = false,
                    slot = 1
                )
            ),
            types = listOf(Type(slot = 1, type = TypeX(name = "grass")))
        )

        val speciesDto = SpeciesDetailsResponseDto(
            captureRate = 45,
            baseHappiness = 70,
            flavorTextEntries = listOf(FlavorTextEntry(flavorText = "A strange seed was planted...\nIt grows with the sun.", language = Language(name = "en"), version = Version())),
            growthRate = GrowthRate(name = "medium"),
            hatchCounter = 20,
            habitat = Habitat(name = "grassland"),
            isBaby = false,
            isLegendary = false,
            isMythical = false,
            shape = Shape(name = "quadruped"),
            color = Color(name = "green")
        )

        val domain = pokemonDto.toDomain(speciesDto)

        // Stats should be mapped
        assertEquals(2, domain.stats.size)
        val hp = domain.stats.first { it.first.value == "hp" }
        assertEquals(45, hp.second.value)

        // Flavor text should be picked and newlines replaced
        assertEquals("A strange seed was planted... It grows with the sun.", domain.about.value)

        // Basic fields
        assertEquals(1, domain.id.value)
        assertEquals("bulbasaur", domain.name.value)
    }
}
