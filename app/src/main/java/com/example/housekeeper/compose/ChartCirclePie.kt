package com.example.housekeeper.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.housekeeper.R
import com.example.housekeeper.presentation.model.ChartModel

@Composable
fun ChartCirclePie(
    charts: List<ChartModel>,
    size: Dp = 200.dp,
    modifier: Modifier = Modifier,
) {
    val placeholder = listOf(
        ChartModel(0.13f, colorResource(R.color.color_1)),
        ChartModel(0.07f, colorResource(R.color.color_2)),
        ChartModel(0.15f, colorResource(R.color.color_3)),
        ChartModel(0.26f, colorResource(R.color.color_5)),
        ChartModel(0.11f, colorResource(R.color.color_6)),
        ChartModel(0.22f, colorResource(R.color.color_7)),
        ChartModel(0.06f, colorResource(R.color.color_8)),
    )

    Canvas(modifier = modifier
        .defaultMinSize(size)
        .aspectRatio(1f).padding(48.dp),

        onDraw = {

            var startAngle = 0f
            var sweepAngle = 0f

            (charts.ifEmpty { placeholder }).forEach {

                sweepAngle = (it.value) * 360

                drawArc(
                    color = it.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                )

                startAngle += sweepAngle
            }

        })
}

@Preview
@Composable
fun ShowChartCirclePie() {
    ChartCirclePie(
        listOf(
            ChartModel(0.13f, colorResource(R.color.color_1)),
            ChartModel(0.07f, colorResource(R.color.color_2)),
            ChartModel(0.15f, colorResource(R.color.color_3)),
            ChartModel(0.26f, colorResource(R.color.color_5)),
            ChartModel(0.11f, colorResource(R.color.color_6)),
            ChartModel(0.22f, colorResource(R.color.color_7)),
            ChartModel(0.06f, colorResource(R.color.color_8)),
        )
    )
}