package com.example.housekeeper.presentation.creator


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.housekeeper.R
import com.example.housekeeper.databinding.FragmentCategoryConstructorBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryConstructorFragment : Fragment() {

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

    private var _binding: FragmentCategoryConstructorBinding? = null
    private val binding get() = _binding!!
    val viewModel by viewModel<ConstructorViewModel>()
    private var simpleTextWatcher: TextWatcher? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryConstructorBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener { findNavController().popBackStack() }

        simpleTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setName(s?.toString() ?: "")
                binding.createButton.isEnabled = binding.inputName.text.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        simpleTextWatcher?.let { binding.inputName.addTextChangedListener(it) }
        viewModel.imageLiveDataObserved().observe(viewLifecycleOwner){
            showImage(it)
        }
        binding.rvIcons.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvIcons.adapter = AddIconAdapter(icons){
            item -> viewModel.setImage(item)
        }
        binding.createButton.setOnClickListener {
            viewModel.createCategory()
            findNavController().popBackStack()
        }

    }

    private fun showImage(src: Int) {
        binding.floatingActionButton.setImageResource(src)
    }
}