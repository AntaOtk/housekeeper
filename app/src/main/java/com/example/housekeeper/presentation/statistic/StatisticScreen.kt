package com.example.housekeeper.presentation.statistic

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.housekeeper.compose.ChartCirclePie
import com.example.housekeeper.compose.StatisticCart
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatisticScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: StatisticViewModel = koinViewModel()
    val categories = viewModel.categories.observeAsState(mutableListOf())
    val sectors = viewModel.sectors.observeAsState(mutableListOf())
    Column {
        if (sectors.value.isNotEmpty()) {
            ChartCirclePie(modifier = modifier, charts = sectors.value)
        } else {
            Box(modifier = modifier.fillMaxSize()) {
                Text(
                    text = "No Date",
                    textAlign = TextAlign.Center,
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }
        for (item in categories.value) {
            if (item.sum < 0.0)
                StatisticCart(item.name, item.sum, item.planingSum ?: 0.0)
        }
    }
    SideEffect {
        Log.d("MY", categories.value.toString())
        Log.d("MY", sectors.value.toString())
    }

}

@Preview
@Composable
fun ShowSpendingItem() {
    StatisticScreen()
}
