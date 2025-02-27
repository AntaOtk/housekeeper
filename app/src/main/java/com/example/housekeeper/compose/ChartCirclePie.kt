package com.example.housekeeper.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.presentation.model.ChartModel

@Composable
fun ChartCirclePie(
    charts: List<ChartModel>,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier
        .size(200.dp)
        .padding(12.dp),

        onDraw = {

            var startAngle = 0f
            var sweepAngle = 0f

            charts.forEach {

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
            ChartModel(0.5f, Color.Cyan),
            ChartModel(0.25f, Color.Magenta),
            ChartModel(0.15f, Color.Blue),
            ChartModel(0.10f, Color.Red)
        )
    )
}