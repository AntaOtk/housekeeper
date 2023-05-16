package com.example.housekeeper

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val icon = itemView.findViewById<ImageView>(R.id.iconGroup)
    private val name = itemView.findViewById<TextView>(R.id.nameGroup)
    private val sum = itemView.findViewById<TextView>(R.id.sumGroup)



    fun bind(model: Expense) {
        name.text = model.name
        sum.text = "%.2f".format(model.sum)
        icon.background.setTint(itemView.context.getColor(model.color))
        icon.setImageResource(model.image)

    }



}