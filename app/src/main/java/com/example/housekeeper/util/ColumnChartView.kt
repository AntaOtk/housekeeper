package com.example.housekeeper.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.example.housekeeper.R

class ColumnChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

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
            recycle()
        }
    }

}

