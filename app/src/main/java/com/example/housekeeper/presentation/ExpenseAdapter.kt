package com.example.housekeeper.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.housekeeper.R
import com.example.housekeeper.databinding.AddButtonBinding
import com.example.housekeeper.databinding.ItemCategoryBinding
import com.example.housekeeper.domain.model.Expense

class ExpenseAdapter(
    private val category: List<Expense>,
    private val addCategory: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_category -> {
                val view =
                    ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ExpenseViewHolder(view)
            }

            R.layout.add_button -> {
                AddViewHolder(
                    AddButtonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_category -> (holder as ExpenseViewHolder).bind(category[position])
            R.layout.add_button -> (holder as AddViewHolder).bind()
        }
    }

    override fun getItemCount() = category.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == category.size) R.layout.add_button else R.layout.item_category
    }

    inner class AddViewHolder(private val binding: AddButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.addButton.setOnClickListener {
                addCategory.invoke()
            }
        }
    }

    inner class ExpenseViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val icon = itemView.findViewById<ImageView>(R.id.iconGroup)
        fun bind(model: Expense) {
            binding.nameGroup.text = model.name
            binding.sumGroup.text = "%.2f".format(model.sum ?: 0.0)
            binding.iconGroup.background.setTint(itemView.context.getColor(R.color.color_2))
            binding.iconGroup.setImageResource(model.image)

            /*    binding.iconGroup.setOnDragListener { v, e ->

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
                            val displayIntent = Intent(v.context, CalculatorFragment::class.java)
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
                }*/
        }
    }
}


