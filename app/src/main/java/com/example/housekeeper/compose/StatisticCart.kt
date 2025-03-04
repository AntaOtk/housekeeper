package com.example.housekeeper.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.housekeeper.R


@Composable
fun StatisticCart(
    name: String,
    currentValue: Double,
    planingValue: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.small_dimen))
            .padding(horizontal = dimensionResource(R.dimen.classic_padding)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.color_9),
        ),
    ) {
        val currentPlan = remember { mutableStateOf(planingValue.toString()) }
        Column(
            modifier
                .padding(dimensionResource(R.dimen.classic_padding))
                .fillMaxWidth()
        ) {
            Row(
                (modifier.fillMaxWidth()).padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(modifier = modifier, text = name, fontSize = 24.sp)
                Row {
                    Text(modifier = modifier, text = "$currentValue /")
                    Text(modifier = modifier.clickable {  }, text = currentPlan.value)
                }
            }
            LinearProgressIndicator(
                progress = { if (currentValue < planingValue) (currentValue / planingValue).toFloat() else 1F },
                modifier = modifier.fillMaxWidth(),
                color = colorResource(R.color.purple_200),
                trackColor = Color.LightGray,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                gapSize = 0.dp
            )
        }
    }
}

@Preview
@Composable
fun ShowStatisticCart() {
    StatisticCart(
        "name", 21.3, 54.0, {}
    )
}
