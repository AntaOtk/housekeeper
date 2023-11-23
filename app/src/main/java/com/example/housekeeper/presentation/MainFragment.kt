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
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    val viewModel by viewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var addCategory: () -> Unit
    private val expenses = mutableListOf<Expense>()
    private val expenseAdapter = ExpenseAdapter(expenses) {
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
        viewModel.getCategories()
        viewModel.observeCategoryLiveData().observe(viewLifecycleOwner) {
            rander(it)
        }
        addCategory =
            { findNavController().navigate(R.id.action_mainFragment_to_categoryConstructorFragment) }
        binding.rvAccount.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvCategory.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvCategory.adapter = expenseAdapter
        binding.rvAccount.adapter = AccountAdapter(accounts,addCategory)
        binding.includedToolbar.addTransactionButton.setOnClickListener {
            findNavController().navigate(id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rander(categories: List<Expense>) {
        expenses.clear()
        expenses.addAll(categories)
        expenseAdapter.notifyDataSetChanged()
    }

    private val accounts = listOf<Expense>(
        Expense(
            null,
            "1",
            2.3,
            R.drawable.euro,
            R.color.color_1
        ),
        Expense(
            null,
            "2",
            12.3,
            R.drawable.euro,
            R.color.color_1
        )

    )

    /* expenses.addAll(
           listOf<Expense>(
               Expense(null,
                   "home",
                   2.3,
                   R.drawable.cosmetic,
                   R.color.color_5
               ),
               Expense(null,
                   "transport",
                   12.3,
                   R.drawable.vaccines,
                   R.color.color_4
               ),
               Expense(null,
                   "product",
                   1.3,
                   R.drawable.cosmetic,
                   R.color.color_6
               ),
               Expense(null,
                   "restorante",
                   12.3,
                   R.drawable.vaccines,
                   R.color.color_8
               ),
               Expense(null,
                   "education",
                   12.3,
                   R.drawable.vaccines,
                   R.color.color_7
               ),

               Expense(null,
                   "hobby",
                   15.3,
                   R.drawable.vaccines,
                   R.color.color_3
               ),
               Expense(null,
                   "pet",
                   18.3,
                   R.drawable.cosmetic,
                   R.color.color_2
               ),
           )
       )*/


}