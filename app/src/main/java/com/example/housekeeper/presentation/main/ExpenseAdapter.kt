package com.example.housekeeper.presentation.main

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
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

            binding.iconGroup.setOnDragListener { v, e ->

                // Handle each of the expected events.
                when (e.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        e.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                    }

                    DragEvent.ACTION_DRAG_ENTERED -> {
                        (v as? ImageView)?.setColorFilter(Color.GREEN)
                        v.invalidate()
                        true
                    }

                    DragEvent.ACTION_DRAG_LOCATION ->
                        true

                    DragEvent.ACTION_DRAG_EXITED -> {
                        true
                    }

                    DragEvent.ACTION_DROP -> {
                        val item: ClipData.Item = e.clipData.getItemAt(0)
                        val dragData = item.text
                        val bundle = bundleOf(ACCOUNT_ID to dragData)
                        bundle.putString(CATEGORY_ID, model.id.toString())
                        v.findNavController()
                            .navigate(R.id.action_mainFragment_to_calculatorFragment, bundle)
                        (v as? ImageView)?.clearColorFilter()
                        v.invalidate()

                        true
                    }

                    DragEvent.ACTION_DRAG_ENDED -> {
                        (v as? ImageView)?.clearColorFilter()
                        v.invalidate()
                        true
                    }

                    else -> {
                        false
                    }
                }
            }

        }
    }

    companion object {
        const val ACCOUNT_ID = "account_id"
        const val CATEGORY_ID = "category_id"

    }
}


