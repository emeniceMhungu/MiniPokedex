package com.assessment.pokedex.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assessment.pokedex.ui.model.BasicInfoNameUiModel
import com.assessment.pokedex.ui.model.BasicInfoValueUiModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicInfoCard(
    modifier: Modifier = Modifier,
    basicInfo: List<Pair<BasicInfoNameUiModel, BasicInfoValueUiModel>>
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Basic Info",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 2
            ) {
                basicInfo.forEach { (name, value) ->
                    InfoItemCard(
                        name = name.value,
                        value = value.value,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}