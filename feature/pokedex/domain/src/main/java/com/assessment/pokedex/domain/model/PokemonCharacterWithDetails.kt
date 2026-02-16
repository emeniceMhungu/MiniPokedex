package com.assessment.pokedex.domain.model


@JvmInline value class About(val value: String) {
//    init {
//        require(value.isNotBlank()) { "About cannot be blank" }
//    }
}

@JvmInline value class PokemonColor(val value: String){
    init {
        require(value.isNotBlank()) { "Pokemon color cannot be blank" }
    }
}
@JvmInline value class PokemonType(val value: String){
    init {
        require(value.isNotBlank()) { "Pokemon type cannot be blank" }
    }
}

@JvmInline value class PokemonAbility(val value: String){
    init {
        require(value.isNotBlank()) { "Pokemon ability cannot be blank" }
    }
}
@JvmInline value class PokemonStatName(val value: String){
    init {
        require(value.isNotBlank()) { "Pokemon stat cannot be blank" }
    }
}
@JvmInline value class PokemonStatValue(val value: Int){
    init {
        require(value >= 0) { "Pokemon stat value cannot be negative" }
    }
}

@JvmInline value class BasicInfoName(val value: String){
    init {
        require(value.isNotBlank()) { "Basic info name cannot be blank" }
    }
}
@JvmInline value class BasicInfoValue(val value: String){
//    init {
//        require(value.isNotBlank()) { "Basic info value cannot be blank" }
//    }
}

data class PokemonCharacterWithDetails(
    val id: PokemonId,
    val name: PokemonName,
    val imageUrl: ImageUrl,
    val about: About,
    val basicInfo: List<Pair<BasicInfoName, BasicInfoValue>>,
    val stats: List<Pair<PokemonStatName, PokemonStatValue>>, //name, value
    val abilities: List<Pair<PokemonAbility, Boolean>>, //name, hidden
    val types: List<PokemonType>,
    val pokemonColor: PokemonColor
)
