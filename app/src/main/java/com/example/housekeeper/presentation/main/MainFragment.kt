package com.example.housekeeper.presentation.main

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
    private val viewModel by viewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var addCategory: () -> Unit
    private val expenses = mutableListOf<Expense>()
    private val accounts = mutableListOf<Expense>()
    private val expenseAdapter = ExpenseAdapter(expenses) {
        addCategory()
    }
    private val accountsAdapter = AccountAdapter(accounts) {
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
        viewModel.observeCategoryLiveData().observe(viewLifecycleOwner) {
            renderCategories(it)
        }
        viewModel.observeAccountLiveData().observe(viewLifecycleOwner) {
            renderAccounts(it)
        }
        addCategory =
            { findNavController().navigate(R.id.action_mainFragment_to_categoryConstructorFragment) }
        binding.rvAccount.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvCategory.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvCategory.adapter = expenseAdapter
        binding.rvAccount.adapter = accountsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderCategories(categories: List<Expense>) {
        expenses.clear()
        expenses.addAll(categories)
        expenseAdapter.notifyDataSetChanged()
    }

    private fun renderAccounts(result: List<Expense>) {
        accounts.clear()
        accounts.addAll(result)
        accountsAdapter.notifyDataSetChanged()
    }

}