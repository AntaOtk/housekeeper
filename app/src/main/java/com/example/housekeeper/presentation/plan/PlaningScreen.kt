package com.example.housekeeper.presentation.plan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.housekeeper.R
import com.example.housekeeper.compose.StatisticCart
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaningScreen(modifier: Modifier = Modifier) {
    val viewModel: PlaningViewModel = koinViewModel()
    val categoryList = viewModel.categories.observeAsState(mutableListOf())
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.classic_padding))
            .verticalScroll(rememberScrollState())
    ) {
        for (item in categoryList.value) {
            StatisticCart(item.name, item.sum, item.planingSum ?: 0.0, {})
        }
    }
}
