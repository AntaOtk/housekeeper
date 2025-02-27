package com.example.housekeeper.presentation.statistic

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.R
import com.example.housekeeper.compose.ChartCirclePie
import com.example.housekeeper.compose.ExpandableCard
import com.example.housekeeper.compose.PlaceHolderCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatisticScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: StatisticViewModel = koinViewModel()
    val categories = viewModel.categories.observeAsState(mutableListOf())
    val sectors = viewModel.sectors.observeAsState(mutableListOf())
    Column {
        Box(
            modifier = modifier
                .defaultMinSize(200.dp, 200.dp)
                .aspectRatio(1f)
        ) {
            ChartCirclePie(
                modifier = modifier
                    .align(Alignment.Center)
                    .padding(
                        dimensionResource(R.dimen.classic_padding)
                    ), charts = sectors.value
            )
            if (sectors.value.isEmpty()) {
                Text(
                    text = "No Date",
                    textAlign = TextAlign.Center,
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }
        if (categories.value.isEmpty()) {
            PlaceHolderCard()
            PlaceHolderCard()
            PlaceHolderCard()
        } else {
            for (item in categories.value) {
                ExpandableCard(item.name)
            }
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
