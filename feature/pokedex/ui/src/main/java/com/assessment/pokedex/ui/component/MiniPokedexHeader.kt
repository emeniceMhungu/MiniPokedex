package com.assessment.pokedex.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.assessment.designsystem.component.MiniPokedexAppBackground
import com.assessment.designsystem.component.ThemePreviews
import com.assessment.designsystem.theme.MiniPokedexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniPokedexHeader(
    modifier: Modifier = Modifier,
    title: String = "Mini Pokedex",
    navIcon: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = navIcon
    )
}

@ThemePreviews
@Composable
fun PreviewMiniPokedexHeader() {
    MiniPokedexTheme {
        MiniPokedexAppBackground(
            modifier = Modifier
                .height(180.dp)
        ) {
            MiniPokedexHeader()
        }

    }
}