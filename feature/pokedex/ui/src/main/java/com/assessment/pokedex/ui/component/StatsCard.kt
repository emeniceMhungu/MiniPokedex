package com.assessment.pokedex.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assessment.pokedex.ui.model.PokemonStatNameUiModel
import com.assessment.pokedex.ui.model.PokemonStatValueUiModel

@Composable
fun StatsCard(
    modifier: Modifier = Modifier,
    stats: List<Pair<PokemonStatNameUiModel, PokemonStatValueUiModel>>
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Base Stats",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                stats.forEach { (name, value) ->
                    StatRow(name = name.value, value = value.value)
                }
            }
        }
    }
}