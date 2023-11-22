package com.example.housekeeper.presentation

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
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
            sum.text = "%.2f".format(model.sum)
            icon.background.setTint(itemView.context.getColor(model.color))
            icon.setImageResource(model.image)

            icon.setOnLongClickListener { v ->
                // Create a new ClipData. This is done in two steps to provide
                // clarity. The convenience method ClipData.newPlainText() can
                // create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag.
                val item = ClipData.Item(v.tag as? CharSequence)

                // Create a new ClipData using the tag as a label, the plain text
                // MIME type, and the already-created item. This creates a new
                // ClipDescription object within the ClipData and sets its MIME type
                // to "text/plain".
                val dragData = ClipData(
                    v.tag as? CharSequence,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    item
                )

                // Instantiate the drag shadow builder.
                val myShadow = object : View.DragShadowBuilder(v) {
                    private val shadow = ColorDrawable(Color.LTGRAY)

                    // Define a callback that sends the drag shadow dimensions and touch point
// back to the system.
                    override fun onProvideShadowMetrics(size: Point, touch: Point) {

                        // Set the width of the shadow to half the width of the original
                        // View.
                        val width: Int = view.width / 2

                        // Set the height of the shadow to half the height of the original
                        // View.
                        val height: Int = view.height / 2

                        // The drag shadow is a ColorDrawable. Set its dimensions to
                        // be the same as the Canvas that the system provides. As a result,
                        // the drag shadow fills the Canvas.
                        shadow.setBounds(0, 0, width, height)

                        // Set the size parameter's width and height values. These get back
                        // to the system through the size parameter.

                        size.set(width, height)

                        // Set the touch point's position to be in the middle of the drag
                        // shadow.
                        touch.set(width / 2, height / 2)
                    }

                    // Define a callback that draws the drag shadow in a Canvas that the system
// constructs from the dimensions passed to onProvideShadowMetrics().
                    override fun onDrawShadow(canvas: Canvas) {

                        // Draw the ColorDrawable on the Canvas passed in from the system.
                        shadow.draw(canvas)
                    }
                }

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


