package com.assessment.minipokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assessment.designsystem.component.MiniPokedexAppBackground
import com.assessment.designsystem.theme.MiniPokedexTheme
import com.assessment.pokedex.ui.model.PokemonDetailsEvent
import com.assessment.pokedex.ui.screen.PokemonCharacterDetailsScreen
import com.assessment.pokedex.ui.viewmodel.GetPokemonDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniPokedexTheme {
                MiniPokedexApp()
            }
        }
    }
}

@Composable
fun MiniPokedexApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        MiniPokedexTheme {
            MiniPokedexAppBackground {
                val detailsViewModel = hiltViewModel<GetPokemonDetailsViewModel>()
                val detailsUiState = detailsViewModel.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    detailsViewModel.onEvent(
                        PokemonDetailsEvent.LoadPokemonDetails(
                            25
                        )
                    )
                }

                PokemonCharacterDetailsScreen(
                    modifier = Modifier.fillMaxSize(),
                    uiState = detailsUiState.value,
                    onNavigateUp = { }
                )
            }
        }

    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
//    FAVORITES("Favorites", Icons.Default.Favorite),
//    PROFILE("Profile", Icons.Default.AccountBox),
}
