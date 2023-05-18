package com.example.housekeeper

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

            icon.setOnDragListener { v, e ->

                // Handle each of the expected events.
                when (e.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        // Determine whether this View can accept the dragged data.
                        if (e.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            // As an example, apply a blue color tint to the View to
                            // indicate that it can accept data.
                            (v as? ImageView)?.setColorFilter(Color.BLUE)

                            // Invalidate the view to force a redraw in the new tint.
                            v.invalidate()

                            // Return true to indicate that the View can accept the dragged
                            // data.
                            true
                        } else {
                            // Return false to indicate that, during the current drag and
                            // drop operation, this View doesn't receive events again until
                            // ACTION_DRAG_ENDED is sent.
                            false
                        }
                    }
                    DragEvent.ACTION_DRAG_ENTERED -> {
                        // Apply a green tint to the View.
                        (v as? ImageView)?.setColorFilter(Color.GREEN)

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate()

                        // Return true. The value is ignored.
                        true
                    }

                    DragEvent.ACTION_DRAG_LOCATION ->
                        // Ignore the event.
                        true
                    DragEvent.ACTION_DRAG_EXITED -> {
                        // Reset the color tint to blue.
                        (v as? ImageView)?.setColorFilter(Color.BLUE)

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate()

                        // Return true. The value is ignored.
                        true
                    }
                    DragEvent.ACTION_DROP -> {
                        // Get the item containing the dragged data.
                        val item: ClipData.Item = e.clipData.getItemAt(0)

                        // Get the text data from the item.
                        val dragData = item.text

                        // Display a message containing the dragged data.
                        val displayIntent = Intent(v.context, Calculator::class.java)
                        v.context.startActivity(displayIntent)

                        // Turn off color tints.
                        (v as? ImageView)?.clearColorFilter()

                        // Invalidate the view to force a redraw.
                        v.invalidate()

                        // Return true. DragEvent.getResult() returns true.
                        true
                    }

                    DragEvent.ACTION_DRAG_ENDED -> {
                        // Turn off color tinting.
                        (v as? ImageView)?.clearColorFilter()

                        // Invalidate the view to force a redraw.
                        v.invalidate()

                        // Do a getResult() and display what happens.
                        when(e.result) {
                            true ->
                                Toast.makeText(icon.context, "The drop was handled.", Toast.LENGTH_LONG)
                            else ->
                                Toast.makeText(icon.context, "The drop didn't work.", Toast.LENGTH_LONG)
                        }.show()

                        // Return true. The value is ignored.
                        true
                    }
                    else -> {
                        // An unknown action type is received.
                        false
                    }
                }
            }

        }
    }
}


