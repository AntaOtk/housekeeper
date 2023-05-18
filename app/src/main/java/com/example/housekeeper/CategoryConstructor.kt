package com.example.housekeeper


import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryConstructor : AppCompatActivity() {

    private val icons = listOf(
        R.drawable.cosmetic,
        R.drawable.travel,
        R.drawable.car_servise,
        R.drawable.clothes,
        R.drawable.vaccines,
        R.drawable.telecom,
        R.drawable.home,
        R.drawable.cosmetic,
        R.drawable.travel,
        R.drawable.car_servise,
        R.drawable.clothes,
        R.drawable.vaccines,
        R.drawable.telecom,
        R.drawable.home,
        R.drawable.cosmetic,
        R.drawable.travel,
        R.drawable.car_servise,
        R.drawable.clothes,
        R.drawable.vaccines,
        R.drawable.telecom,
        R.drawable.home
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_constructor)

        val imageBack = findViewById<ImageButton>(R.id.backButton)
        imageBack.setOnClickListener { finish() }

        val iconsView = findViewById<RecyclerView>(R.id.rvIcons)

        iconsView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)

        iconsView.adapter = AddIconAdapter(icons)

    }
}