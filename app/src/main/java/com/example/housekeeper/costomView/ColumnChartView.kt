package com.example.housekeeper.costomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.example.housekeeper.R
import com.example.housekeeper.costomView.model.Item

class ColumnChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var items: List<Item> = emptyList()
    private val barWidth: Float
    private val barColor: Int

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.ColumnChartView,
            defStyleAttr,
            defStyleRes
        ).apply {
            barColor = getColor(
                R.styleable.ColumnChartView_barColor,
                ContextCompat.getColor(context, R.color.chart_bar_default_line_color)
            )
            barWidth = getDimension(
                R.styleable.ColumnChartView_barWidth,
                resources.getDimension(R.dimen.default_column_chart_width)
            )

            recycle()
        }
    }

    private val chartBarPaint = Paint().apply {
        strokeWidth = barWidth
        color = barColor
    }


    private var maxLevelY: Float = 0F
    private var minLevelY: Float = 0F

    private fun levelY(value: Long): Float {
        val levelRate = value / items.maxOf { it.value }.toFloat()
        return minLevelY - (minLevelY - maxLevelY) * levelRate
    }

    private var chartItemWidth: Float = 0F
    private fun barX(number: Long): Float {
        return chartItemWidth * number + chartItemWidth / 2
    }

    private val bars: Map<Item, RectF> by lazy {
        items.associateWith { item ->
            val centerX = barX(item.category.id ?: 0)

            RectF(
                centerX- barWidth / 2F,
                levelY(item.value),
                centerX + barWidth / 2F,
                minLevelY
            )
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chartItemWidth = width / items.size.toFloat()
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        drawBars()
    }

    private fun Canvas.drawBars() {
        bars.forEach { (_, rect) ->
            drawRect(rect, chartBarPaint)
        }
    }
}

