package com.example.housekeeper.presentation.main

import android.content.ClipData
import android.content.ClipDescription
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.housekeeper.R
import com.example.housekeeper.domain.model.Expense

class AccountAdapter(
    private val category: List<Expense>,
    private val addAccount: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_category -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_category, parent, false)
                ExpenseViewHolder(view)
            }

            R.layout.add_button -> {
                val view = LayoutInflater.from(parent.context)
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
                addAccount.invoke()
            }


        }
    }

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val icon = itemView.findViewById<ImageView>(R.id.iconGroup)
        private val name = itemView.findViewById<TextView>(R.id.nameGroup)
        private val sum = itemView.findViewById<TextView>(R.id.sumGroup)


        fun bind(model: Expense) {
            name.text = model.name
            sum.text = "%.2f".format(model.sum ?: 0.0)
            icon.background.setTint(itemView.context.getColor(R.color.color_6))
            icon.setImageResource(model.image)

            icon.setOnLongClickListener { v ->
                val item = ClipData.Item(model.id.toString())
                Log.d("drag","model.id ${model.id}")
                Log.d("drag","dragData $item")


                // Create a new ClipData using the tag as a label, the plain text
                // MIME type, and the already-created item. This creates a new
                // ClipDescription object within the ClipData and sets its MIME type
                // to "text/plain".
                val dragData = ClipData(
                    v.tag as? CharSequence,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    item
                )
                Log.d("drag","dragData $dragData")


                // Instantiate the drag shadow builder.
                val myShadow = object : View.DragShadowBuilder(v) {}

                // Start the drag.
                v.startDragAndDrop(
                    dragData,  // The data to be dragged.
                    myShadow,  // The drag shadow builder.
                    null,      // No need to use local data.
                    0          // Flags. Not currently used, set to 0.
                )

                // Indicate that the long-click is handled.
                true
            }
        }
    }
}


