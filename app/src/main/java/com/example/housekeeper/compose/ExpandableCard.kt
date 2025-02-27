package com.example.housekeeper.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.domain.model.Transaction

@Composable
fun ExpandableCard(
    title: String,
    color: Color = Color.LightGray,
) {
    var expanded by remember { mutableStateOf(false) }
    var trasactions by remember { mutableStateOf(listOf<Transaction>()) }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                expanded = !expanded
            }
    )
    {
        Column(
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(8.dp)
            )
            if (expanded) {
                Text(
                    text = "Content Sample for Display on Expansion of Card",
                    modifier = Modifier.padding(8.dp)
                )
                Column {
                    for (transaction in trasactions){
                        Text(text = "sds  ${transaction.sum}")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowExpandableCard() {
    ExpandableCard(
        "name")
}