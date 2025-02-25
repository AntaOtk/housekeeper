package com.example.housekeeper.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.R

@Composable
fun ExpensesItem(
    name: String,
    currentValue: Double,
    planingValue: Double,
    image: Int,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material.Card(
        modifier
            .padding(dimensionResource(R.dimen.medium_dimen)).size(100.dp), shape = RoundedCornerShape(16.dp),
        backgroundColor = colorResource(R.color.color_9)
    ) {
        Column(
            modifier = modifier
                .padding(vertical = (dimensionResource(R.dimen.medium_dimen))),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = name,
                maxLines = 1,
            )
            Icon(
                modifier = modifier
                    .padding(vertical = (dimensionResource(R.dimen.medium_dimen)))
                    .align(Alignment.CenterHorizontally)
                    .size(20.dp),
                painter = painterResource(image),
                contentDescription = ""
            )
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = "$currentValue / $planingValue"
            )
        }
    }
}

@Preview
@Composable
fun ShowFullExpensesItem() {
    ExpensesItem(
        "name", 33.0, 54.0, R.drawable.cosmetic
    )
}

@Preview
@Composable
fun ShowExpensesItem() {
    ExpensesItem(
        "name", 13.0, 54.0, R.drawable.cosmetic
    )
}
