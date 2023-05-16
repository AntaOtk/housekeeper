package com.example.housekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val mContext: Context,
    private val category: List<Expense>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_category -> {
                val view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_category, parent, false)
                ExpenseViewHolder(view)
            }
            R.layout.add_button -> {
                val view = LayoutInflater.from(mContext)
                    .inflate(R.layout.add_button, parent, false)
                PlaceholderViewHolder(view)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_category -> (holder as ExpenseViewHolder).bind(category[position])
            R.layout.add_button -> (holder as PlaceholderViewHolder).bind()
        }
    }

    override fun getItemCount() = category.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == category.size) R.layout.add_button else R.layout.item_category
    }

    inner class PlaceholderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val btn = itemView.findViewById<ImageView>(R.id.addButton)

        fun bind() {
            btn.setOnClickListener {
                val displayIntent = Intent(it.context, CategoryConstructor::class.java)
                it.context.startActivity(displayIntent)
            }


        }
    }

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val icon = itemView.findViewById<ImageView>(R.id.iconGroup)
        private val name = itemView.findViewById<TextView>(R.id.nameGroup)
        private val sum = itemView.findViewById<TextView>(R.id.sumGroup)


        fun bind(model: Expense) {
            name.text = model.name
            sum.text = "%.2f".format(model.sum)
            icon.background.setTint(itemView.context.getColor(model.color))
            icon.setImageResource(model.image)

            icon.setOnClickListener {
                val displayIntent = Intent(it.context, Calculator::class.java)
                it.context.startActivity(displayIntent)
            }

        }
    }
}


