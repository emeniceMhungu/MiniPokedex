package com.assessment.network.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class SpeciesDetailsResponseDto(
    @param:Json(name = "base_happiness")
    val baseHappiness: Int = 0,
    @param:Json(name = "capture_rate")
    val captureRate: Int = 0,
    @param:Json(name = "color")
    val color: Color = Color(),
    @param:Json(name = "egg_groups")
    val eggGroups: List<EggGroup> = listOf(),
    @param:Json(name = "evolution_chain")
    val evolutionChain: EvolutionChain = EvolutionChain(),
    @param:Json(name = "evolves_from_species")
    val evolvesFromSpecies: Any? = Any(),
    @param:Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry> = listOf(),
    @param:Json(name = "form_descriptions")
    val formDescriptions: List<Any?> = listOf(),
    @param:Json(name = "forms_switchable")
    val formsSwitchable: Boolean = false,
    @param:Json(name = "gender_rate")
    val genderRate: Int = 0,
    @param:Json(name = "genera")
    val genera: List<Genera> = listOf(),
    @param:Json(name = "generation")
    val generation: Generation = Generation(),
    @param:Json(name = "growth_rate")
    val growthRate: GrowthRate = GrowthRate(),
    @param:Json(name = "habitat")
    val habitat: Habitat = Habitat(),
    @param:Json(name = "has_gender_differences")
    val hasGenderDifferences: Boolean = false,
    @param:Json(name = "hatch_counter")
    val hatchCounter: Int = 0,
    @param:Json(name = "id")
    val id: Int = 0,
    @param:Json(name = "is_baby")
    val isBaby: Boolean = false,
    @param:Json(name = "is_legendary")
    val isLegendary: Boolean = false,
    @param:Json(name = "is_mythical")
    val isMythical: Boolean = false,
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "names")
    val names: List<Name> = listOf(),
    @param:Json(name = "order")
    val order: Int = 0,
    @param:Json(name = "pal_park_encounters")
    val palParkEncounters: List<PalParkEncounter> = listOf(),
    @param:Json(name = "pokedex_numbers")
    val pokedexNumbers: List<PokedexNumber> = listOf(),
    @param:Json(name = "shape")
    val shape: Shape = Shape(),
    @param:Json(name = "varieties")
    val varieties: List<Variety> = listOf()
)

@JsonClass(generateAdapter = true)
data class Color(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class EggGroup(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class EvolutionChain(
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    @param:Json(name = "flavor_text")
    val flavorText: String = "",
    @param:Json(name = "language")
    val language: Language = Language(),
    @param:Json(name = "version")
    val version: Version = Version()
)

@JsonClass(generateAdapter = true)
data class Genera(
    @param:Json(name = "genus")
    val genus: String = "",
    @param:Json(name = "language")
    val language: Language = Language()
)

@JsonClass(generateAdapter = true)
data class GrowthRate(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Habitat(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Name(
    @param:Json(name = "language")
    val language: Language = Language(),
    @param:Json(name = "name")
    val name: String = ""
)

@JsonClass(generateAdapter = true)
data class PalParkEncounter(
    @param:Json(name = "area")
    val area: Area = Area(),
    @param:Json(name = "base_score")
    val baseScore: Int = 0,
    @param:Json(name = "rate")
    val rate: Int = 0
)

@JsonClass(generateAdapter = true)
data class PokedexNumber(
    @param:Json(name = "entry_number")
    val entryNumber: Int = 0,
    @param:Json(name = "pokedex")
    val pokedex: Pokedex = Pokedex()
)

@JsonClass(generateAdapter = true)
data class Shape(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Variety(
    @param:Json(name = "is_default")
    val isDefault: Boolean = false,
    @param:Json(name = "pokemon")
    val pokemon: Pokemon = Pokemon()
)

@JsonClass(generateAdapter = true)
data class Language(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Area(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Pokedex(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Pokemon(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)
