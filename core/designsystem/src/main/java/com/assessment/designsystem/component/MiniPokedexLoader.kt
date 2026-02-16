package com.assessment.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assessment.designsystem.theme.MiniPokedexTheme

@Composable
fun MiniPokedexLoader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(48.dp)
        )
    }
}

@ThemePreviews
@Composable
fun WeatherLoaderPreview() {
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            MiniPokedexLoader()
        }
    }
}