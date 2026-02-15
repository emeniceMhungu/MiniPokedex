package com.assessment.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.assessment.designsystem.R
import com.assessment.designsystem.theme.MiniPokedexTheme

@Composable
fun MiniPokedexAppError(messageResId: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(messageResId),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@ThemePreviews
@Composable
fun MiniPokedexAppErrorPreview(){
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            MiniPokedexAppError(messageResId = R.string.error_generic)
        }
    }
}