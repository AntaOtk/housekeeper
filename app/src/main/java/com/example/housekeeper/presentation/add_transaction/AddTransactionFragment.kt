package com.example.housekeeper.presentation.add_transaction


import android.app.DatePickerDialog
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
import com.example.housekeeper.presentation.main.ExpenseAdapter.Companion.ACCOUNT_ID
import com.example.housekeeper.presentation.main.ExpenseAdapter.Companion.CATEGORY_ID
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.xml.datatype.DatatypeConstants.MONTHS


class AddTransactionFragment : Fragment() {

    private val viewModel by viewModel<AddTransactionViewModel>()

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    private val categories = mutableListOf<Expense>()
    private val categoriesAdapter = CategoryAdapter(categories) { category ->
        viewModel.setCategory(category)
    }

    private val accounts = mutableListOf<Expense>()
    private val accountsAdapter = CategoryAdapter(categories) { account ->
        viewModel.setAccount(account)
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
        binding.fromAccount.text =  arguments?.getString(ACCOUNT_ID)
        viewModel.setAccountFromID(arguments?.getString(CATEGORY_ID)?.toLong())
        val bottomCategoriesSheetBehavior = BottomSheetBehavior.from(binding.accountBottomSheet).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }
        bottomCategoriesSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }

                    else -> {
                        viewModel.showCategories()
                    }
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
        setData()

        binding.toAccount.setOnClickListener {
            bottomCategoriesSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val sumTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSum(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        sumTextWatcher.let { binding.etSum.addTextChangedListener(it) }
        binding.accountRv.adapter = categoriesAdapter

        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransaction()
            findNavController().popBackStack()
        }

        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) {
            renderCategories(it)
        }
        viewModel.observeToAccount().observe(viewLifecycleOwner) {
            bottomCategoriesSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            setCategory(it.name)
        }
        viewModel.observeEnabledState().observe(viewLifecycleOwner){
            binding.addTransactionButton.isEnabled =it
        }
    }

    private fun setData() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        binding.etData.setText("$day.$month.$year")
        binding.etData.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                binding.etData.setText("$dayOfMonth.$month.$year")
            }, year, month, day)
            dpd.show()
        }
    }

    private fun setCategory(name: String) {
        binding.toAccount.text = name
    }

    private fun renderCategories(list: List<Expense>) {
        categories.clear()
        categories.addAll(list)
        categoriesAdapter.notifyDataSetChanged()
    }
}