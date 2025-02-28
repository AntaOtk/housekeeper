package com.example.housekeeper.presentation.main.compose

import android.annotation.SuppressLint
import android.content.ClipDescription
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.R
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.util.Constants.accountTransferData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesItem(
    categoryItem: Expense,
    navigateToTransaction: (Expense?, Expense?) -> Unit,
    modifier: Modifier = Modifier
) {
    val dragAndDropTarget = remember {
        object : DragAndDropTarget {
            @SuppressLint("NewApi")
            override fun onDrop(event: DragAndDropEvent): Boolean {
                Log.d("DND", "ds")
                try {
                    val data =
                        event.toAndroidDragEvent().clipData.getItemAt(0)?: return false
                    Log.d("DND2", data.toString())
                    val accountItem = data.intent.getParcelableExtra(accountTransferData, Expense::class.java)
                    Log.d("DND3", accountItem.toString())
                    navigateToTransaction(accountItem, categoryItem)
                } catch (e: Exception) {
                    Log.d("", e.toString())
                }
                return true
            }
        }
    }
    Card(
        modifier
            .padding(dimensionResource(R.dimen.medium_dimen))
            .size(100.dp)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event
                        .mimeTypes()
                        .contains(ClipDescription.MIMETYPE_TEXT_INTENT)
                }, dragAndDropTarget
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.color_9),
        ),
    ) {
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = (dimensionResource(R.dimen.medium_dimen))),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = categoryItem.name,
                maxLines = 1,
            )
            Icon(
                modifier = modifier
                    .padding(vertical = (dimensionResource(R.dimen.medium_dimen)))
                    .align(Alignment.CenterHorizontally)
                    .size(20.dp),
                painter = painterResource(categoryItem.image),
                contentDescription = ""
            )
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = "${categoryItem.sum} / ${categoryItem.planingSum}"
            )
        }
    }
}

@Preview
@Composable
fun ShowFullExpensesItem() {
    ExpensesItem(
        Expense(
            null,
            "home",
            23.0,
            null,
            R.drawable.home,
        ),
        navigateToTransaction = { _, _ -> },
    )
}

@Preview
@Composable
fun ShowExpensesItem() {
    ExpensesItem(
        Expense(
            null,
            "home",
            23.0,
            34.0,
            R.drawable.home,
        ),
        navigateToTransaction = { _, _ -> },
    )
}
