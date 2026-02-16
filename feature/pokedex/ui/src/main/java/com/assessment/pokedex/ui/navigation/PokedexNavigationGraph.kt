package com.assessment.pokedex.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.assessment.pokedex.ui.model.UiPokemonId
import kotlinx.serialization.Serializable

@Serializable
object PokemonListDestination

@Serializable
data class PokemonDetailsDestination(val pokemonId: Int)

fun NavGraphBuilder.pokedexNavGraph(
    navController: NavHostController
) {
    composable<PokemonListDestination> {
        PokemonListRoute(
            onPokemonClick = { pokemonId ->
                navController.navigate(PokemonDetailsDestination(pokemonId.value))
            }
        )
    }

    composable<PokemonDetailsDestination> { backStackEntry ->
        val pokemonDetailsDestination: PokemonDetailsDestination = backStackEntry.toRoute()
        PokemonDetailsRoute(
            onNavigateUp = { navController.navigateUp() },
            pokemonId = UiPokemonId(pokemonDetailsDestination.pokemonId)
        )
    }
}
