package com.assessment.pokedex.domain.model

import com.assessment.common.domain.model.BaseResult
import com.assessment.common.domain.model.PokemonError

internal typealias PokemonListResult = BaseResult<List<PokemonCharacter>, PokemonError>