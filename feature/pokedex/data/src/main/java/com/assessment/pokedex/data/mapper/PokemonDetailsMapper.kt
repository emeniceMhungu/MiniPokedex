package com.assessment.pokedex.data.mapper

import com.assessment.network.api.model.PokemonDetailsResponseDto
import com.assessment.network.api.model.SpeciesDetailsResponseDto
import com.assessment.pokedex.domain.model.About
import com.assessment.pokedex.domain.model.BasicInfoName
import com.assessment.pokedex.domain.model.BasicInfoValue
import com.assessment.pokedex.domain.model.ImageUrl
import com.assessment.pokedex.domain.model.PokemonAbility
import com.assessment.pokedex.domain.model.PokemonCharacterWithDetails
import com.assessment.pokedex.domain.model.PokemonColor
import com.assessment.pokedex.domain.model.PokemonId
import com.assessment.pokedex.domain.model.PokemonName
import com.assessment.pokedex.domain.model.PokemonStatName
import com.assessment.pokedex.domain.model.PokemonStatValue
import com.assessment.pokedex.domain.model.PokemonType
import java.util.Locale

fun PokemonDetailsResponseDto.toDomain(speciesResponse: SpeciesDetailsResponseDto): PokemonCharacterWithDetails {
    return PokemonCharacterWithDetails(
        id = PokemonId(id),
        name = PokemonName(name),
        imageUrl = ImageUrl(buildSpriteUrl(id, true)),
        about = About(speciesResponse.flavorTextEntries.firstOrNull {
            it.language.name.contentEquals(Locale.ENGLISH.language, true)
        }?.flavorText
            ?.replace("\n", " ").orEmpty()),
        basicInfo = listOf(
            Pair(BasicInfoName("Height"),
                BasicInfoValue(height.toString())
            ),
            Pair(BasicInfoName("Weight"),
                BasicInfoValue(weight.toString())
            ),
            Pair(BasicInfoName("Base Experience"),
                BasicInfoValue(baseExperience.toString())
            ),
            Pair(BasicInfoName("Capture Rate"),
                BasicInfoValue(speciesResponse.captureRate.toString())
            ),
            Pair(BasicInfoName("Base Happiness"),
                BasicInfoValue(speciesResponse.baseHappiness.toString())
            ),
            Pair(BasicInfoName("Gender Rate"),
                BasicInfoValue(speciesResponse.genderRate.toString())
            ),
            Pair(BasicInfoName("Growth Rate"),
                BasicInfoValue(speciesResponse.growthRate.name)
            ),
            Pair(BasicInfoName("Hatch Counter"),
                BasicInfoValue(speciesResponse.hatchCounter.toString())
            ),
            Pair(BasicInfoName("Habitat"),
                BasicInfoValue(speciesResponse.habitat.name)
            ),
            Pair(BasicInfoName("Is Baby"),
                BasicInfoValue(value = if(speciesResponse.isBaby) "Yes" else "No" )
            ),
            Pair(BasicInfoName("Is Legendary"),
                BasicInfoValue(value = if(speciesResponse.isLegendary) "Yes" else "No" )
            ),
            Pair(BasicInfoName("Is Mythical"),
                BasicInfoValue(value = if(speciesResponse.isMythical) "Yes" else "No" )
            ),
            Pair(BasicInfoName("Shape"),
                BasicInfoValue(speciesResponse.shape.name)
            ),
        ),
        stats = stats.map {
            Pair(PokemonStatName(it.stat.name), PokemonStatValue(it.baseStat))
        },
        abilities = abilities.map {
            Pair(PokemonAbility(it.ability.name), it.isHidden)
        },
        types = types.map {
            PokemonType(it.type.name)
        },
        pokemonColor = PokemonColor(speciesResponse.color.name),
    )
}
