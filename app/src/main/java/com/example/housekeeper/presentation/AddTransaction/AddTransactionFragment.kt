package com.example.housekeeper.presentation.AddTransaction


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.housekeeper.databinding.FragmentCalculatorBinding
import com.example.housekeeper.domain.model.Expense
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTransactionFragment : Fragment() {

    private val viewModel by viewModel<AddTransactionViewModel>()

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val categories = mutableListOf<Expense>()
    val adapter = CategoryAdapter(categories) { category ->
        viewModel.setCategory(category)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener { findNavController().popBackStack() }

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.accountBottomSheet).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }

                    else -> {
                        viewModel.showAccount()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        binding.toAccount.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        }
        val sumTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSum(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        sumTextWatcher.let { binding.sumEt.addTextChangedListener(it) }
        binding.accountRv.adapter = adapter

        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransaction()
            findNavController().popBackStack()
        }

        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) {
            renderCategories(it)
        }
        viewModel.observeFromAccountId().observe(viewLifecycleOwner) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            setCategory(it.name)
        }
    }

    private fun setCategory(name: String) {
        binding.toAccount.text = name
    }

    private fun renderCategories(list: List<Expense>) {
        categories.clear()
        categories.addAll(list)
        adapter.notifyDataSetChanged()
    }
}