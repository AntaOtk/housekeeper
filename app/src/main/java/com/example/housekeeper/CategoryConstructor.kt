package com.example.housekeeper


import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CategoryConstructor: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_constructor)

        val imageBack = findViewById<ImageButton>(R.id.backButton)
        imageBack.setOnClickListener { finish() }

    }
}