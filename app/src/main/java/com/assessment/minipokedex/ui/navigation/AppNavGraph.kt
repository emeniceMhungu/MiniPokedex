package com.assessment.minipokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.assessment.pokedex.ui.navigation.PokemonListDestination
import com.assessment.pokedex.ui.navigation.pokedexNavGraph

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PokemonListDestination,
    ) {
        pokedexNavGraph(navController)

        // can add more destinations here
    }
}