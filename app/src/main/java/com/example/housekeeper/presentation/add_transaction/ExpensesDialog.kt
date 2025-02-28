package com.example.housekeeper.presentation.add_transaction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.housekeeper.R
import com.example.housekeeper.domain.model.Expense

@Composable
fun ExpensesDialog(
    content: List<Expense>,
    onClick: (Expense) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column {
                for (item in content) {
                    Text(
                        modifier = modifier
                            .padding(
                                dimensionResource(R.dimen.classic_padding)
                            )
                            .clickable {
                                onClick(item)
                                onDismissRequest()
                            },
                        text = item.name
                    )
                }
            }
        }
    }
}
