package com.assessment.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.assessment.designsystem.R
import com.assessment.designsystem.icon.MiniPokedexAppIcons
import com.assessment.designsystem.theme.MiniPokedexTheme

@Composable
fun MiniPokedexAppError(
    modifier: Modifier = Modifier,
    messageResId: Int,
    onRetry: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(messageResId),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(onClick = onRetry) {
                Icon(
                    imageVector = MiniPokedexAppIcons.Refresh,
                    contentDescription = stringResource(R.string.retry)
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun MiniPokedexAppErrorPreview(){
    MiniPokedexTheme {
        MiniPokedexAppBackground {
            MiniPokedexAppError(
                messageResId = R.string.error_generic,
                onRetry = {}
            )
        }
    }
}
