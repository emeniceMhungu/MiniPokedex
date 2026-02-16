package com.assessment.minipokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.assessment.designsystem.component.MiniPokedexAppBackground
import com.assessment.designsystem.theme.MiniPokedexTheme
import com.assessment.minipokedex.ui.navigation.AppNavGraph
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
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            MiniPokedexAppBackground {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}

