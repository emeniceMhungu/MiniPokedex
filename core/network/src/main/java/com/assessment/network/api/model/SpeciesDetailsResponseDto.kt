package com.assessment.network.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class SpeciesDetailsResponseDto(
    @field:Json(name = "base_happiness")
    val baseHappiness: Int = 0,
    @field:Json(name = "capture_rate")
    val captureRate: Int = 0,
    @field:Json(name = "color")
    val color: Color = Color(),
    @field:Json(name = "egg_groups")
    val eggGroups: List<EggGroup> = listOf(),
    @field:Json(name = "evolution_chain")
    val evolutionChain: EvolutionChain = EvolutionChain(),
    @field:Json(name = "evolves_from_species")
    val evolvesFromSpecies: Any? = Any(),
    @field:Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry> = listOf(),
    @field:Json(name = "form_descriptions")
    val formDescriptions: List<Any?> = listOf(),
    @field:Json(name = "forms_switchable")
    val formsSwitchable: Boolean = false,
    @field:Json(name = "gender_rate")
    val genderRate: Int = 0,
    @field:Json(name = "genera")
    val genera: List<Genera> = listOf(),
    @field:Json(name = "generation")
    val generation: Generation = Generation(),
    @field:Json(name = "growth_rate")
    val growthRate: GrowthRate = GrowthRate(),
    @field:Json(name = "habitat")
    val habitat: Habitat = Habitat(),
    @field:Json(name = "has_gender_differences")
    val hasGenderDifferences: Boolean = false,
    @field:Json(name = "hatch_counter")
    val hatchCounter: Int = 0,
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "is_baby")
    val isBaby: Boolean = false,
    @field:Json(name = "is_legendary")
    val isLegendary: Boolean = false,
    @field:Json(name = "is_mythical")
    val isMythical: Boolean = false,
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "names")
    val names: List<Name> = listOf(),
    @field:Json(name = "order")
    val order: Int = 0,
    @field:Json(name = "pal_park_encounters")
    val palParkEncounters: List<PalParkEncounter> = listOf(),
    @field:Json(name = "pokedex_numbers")
    val pokedexNumbers: List<PokedexNumber> = listOf(),
    @field:Json(name = "shape")
    val shape: Shape = Shape(),
    @field:Json(name = "varieties")
    val varieties: List<Variety> = listOf()
)

@JsonClass(generateAdapter = true)
data class Color(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class EggGroup(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class EvolutionChain(
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    @field:Json(name = "flavor_text")
    val flavorText: String = "",
    @field:Json(name = "language")
    val language: Language = Language(),
    @field:Json(name = "version")
    val version: Version = Version()
)

@JsonClass(generateAdapter = true)
data class Genera(
    @field:Json(name = "genus")
    val genus: String = "",
    @field:Json(name = "language")
    val language: Language = Language()
)

@JsonClass(generateAdapter = true)
data class GrowthRate(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Habitat(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Name(
    @field:Json(name = "language")
    val language: Language = Language(),
    @field:Json(name = "name")
    val name: String = ""
)

@JsonClass(generateAdapter = true)
data class PalParkEncounter(
    @field:Json(name = "area")
    val area: Area = Area(),
    @field:Json(name = "base_score")
    val baseScore: Int = 0,
    @field:Json(name = "rate")
    val rate: Int = 0
)

@JsonClass(generateAdapter = true)
data class PokedexNumber(
    @field:Json(name = "entry_number")
    val entryNumber: Int = 0,
    @field:Json(name = "pokedex")
    val pokedex: Pokedex = Pokedex()
)

@JsonClass(generateAdapter = true)
data class Shape(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Variety(
    @field:Json(name = "is_default")
    val isDefault: Boolean = false,
    @field:Json(name = "pokemon")
    val pokemon: Pokemon = Pokemon()
)

@JsonClass(generateAdapter = true)
data class Language(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Area(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Pokedex(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Pokemon(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

