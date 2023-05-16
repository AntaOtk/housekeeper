package com.example.housekeeper


import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Calculator: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

            val imageBack = findViewById<ImageButton>(R.id.backButton)
            imageBack.setOnClickListener { finish() }

    }
}