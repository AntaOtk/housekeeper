package com.example.housekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val category = findViewById<RecyclerView>(R.id.rvCategory)
        val account = findViewById<RecyclerView>(R.id.rvAccount)


        account.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        category.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)

        category.adapter = ExpenseAdapter(this,expenses)
        account.adapter = ExpenseAdapter(this,accounts)
    }

    val accounts = listOf<Expense>(
        Expense(
            "1",
            2.3,
            R.drawable.euro,
            R.color.color_1
        ),
        Expense(
            "2",
            12.3,
            R.drawable.euro,
            R.color.color_1
        )

    )

    val expenses = listOf<Expense>(
        Expense(
            "home",
            2.3,
            R.drawable.cosmetic,
            R.color.color_5
        ),
        Expense(
            "transport",
            12.3,
            R.drawable.vaccines,
            R.color.color_4
        ),
        Expense(
            "product",
            1.3,
            R.drawable.cosmetic,
            R.color.color_6
        ),
        Expense(
            "restorante",
            12.3,
            R.drawable.vaccines,
            R.color.color_8
        ),
        Expense(
            "education",
            12.3,
            R.drawable.vaccines,
            R.color.color_7
        ),

        Expense(
            "hobby",
            15.3,
            R.drawable.vaccines,
            R.color.color_3
        ),
        Expense(
            "pet",
            18.3,
            R.drawable.cosmetic,
            R.color.color_2
        ),
    )
}