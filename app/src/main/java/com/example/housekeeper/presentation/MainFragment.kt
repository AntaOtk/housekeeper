package com.example.housekeeper.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.housekeeper.R
import com.example.housekeeper.databinding.FragmentMainBinding
import com.example.housekeeper.domain.model.Expense

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var addCategory: () -> Unit
    private val expenses = mutableListOf<Expense>()
    private val expenseAdapter = ExpenseAdapter(expenses){
        addCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCategory =
            { findNavController().navigate(R.id.action_mainFragment_to_categoryConstructorFragment) }
        binding.rvAccount.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvCategory.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvCategory.adapter = expenseAdapter
        // binding.rvAccount.adapter = AccountAdapter(accounts,addCategory)

        expenses.addAll(
            listOf<Expense>(
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
        )
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


}