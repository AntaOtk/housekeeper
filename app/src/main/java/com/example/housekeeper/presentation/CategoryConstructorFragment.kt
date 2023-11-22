package com.example.housekeeper.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.housekeeper.R
import com.example.housekeeper.databinding.FragmentCategoryConstructorBinding

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

        binding.rvIcons.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)

        binding.rvIcons.adapter = AddIconAdapter(icons)

    }
}