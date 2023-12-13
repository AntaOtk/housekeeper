package com.example.housekeeper.presentation.AddTransaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.housekeeper.databinding.ItemAccountBinding
import com.example.housekeeper.domain.model.Expense

class CategoryAdapter(
    private val category: List<Expense>,
    private val clickListener: (Expense) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return category.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(category[position])
        holder.itemView.setOnClickListener {
            clickListener.invoke(category[position])
        }
    }
}

class CategoryViewHolder(private val binding: ItemAccountBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(account: Expense) {
        binding.accountItem.text = account.name
    }
}
