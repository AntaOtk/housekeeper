package com.example.housekeeper.presentation.creator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.housekeeper.R

class AddIconAdapter (
    private val icons: List<Int>,
    val onClickFunction: (Int) -> Unit) : RecyclerView.Adapter<AddIconViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddIconViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        return AddIconViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddIconViewHolder, position: Int) {
        holder.bind(icons[position])
        holder.itemView.setOnClickListener {
            onClickFunction.invoke(icons[position])
        }
    }

    override fun getItemCount() = icons.size
}

class AddIconViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val icon = itemView.findViewById<ImageView>(R.id.addIcon)

    fun bind(item : Int){
        icon.setImageResource(item)

    }
}