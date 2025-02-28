package com.example.housekeeper.presentation.main.compose

import android.content.ClipData
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.R
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.util.Constants.accountTransferData


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpendingItem(
    account: Expense,
    modifier: Modifier = Modifier,
) {
    val textMeasurer = rememberTextMeasurer()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.small_dimen))
            .dragAndDropSource(
                drawDragDecoration = {
                    drawRect(
                        color = Color.White,
                        topLeft = Offset(x = 0f, y = size.height / 4),
                        size = Size(size.width, size.height / 2)
                    )
                    val textLayoutResult = textMeasurer.measure(
                        text = AnnotatedString(account.name),
                        layoutDirection = layoutDirection,
                        density = this
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(
                            x = (size.width - textLayoutResult.size.width) / 16,
                            y = (size.height - textLayoutResult.size.height) / 2,
                        )
                    )
                    val secondTextLayoutResult = textMeasurer.measure(
                        text = AnnotatedString(account.sum.toString()),
                        layoutDirection = layoutDirection,
                        density = this
                    )
                    drawText(
                        textLayoutResult = secondTextLayoutResult,
                        topLeft = Offset(
                            x = (size.width - secondTextLayoutResult.size.width) - (size.width - textLayoutResult.size.width) / 16,
                            y = (size.height - secondTextLayoutResult.size.height) / 2,
                        )
                    )
                }
            ) {
                detectDragGestures(
                    onDragStart = {
                        Log.d("DND", account.toString())
                        startTransfer(
                            DragAndDropTransferData(
                                clipData = ClipData.newIntent(
                                    "account",
                                    Intent().apply {
                                        Log.d("DND", account.toString())
                                        putExtra(
                                            accountTransferData,
                                            account
                                        )
                                    },
                                )
                            )
                        )
                    },
                    onDrag = { _, _ -> },
                )
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = account.name, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = account.sum.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    R.color.purple_200
                )
            )
        }

    }
}

@Preview
@Composable
fun ShowSpendingItem() {
    SpendingItem(
        Expense(
            101,
            "cash",
            23.0,
            null,
            R.drawable.euro,
        )
    )
}
