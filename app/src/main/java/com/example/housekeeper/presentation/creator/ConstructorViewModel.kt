package com.example.housekeeper.presentation.creator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housekeeper.R
import com.example.housekeeper.domain.CategoryInteractor
import com.example.housekeeper.domain.model.Expense
import kotlinx.coroutines.launch

class ConstructorViewModel(val interactor: CategoryInteractor) : ViewModel() {

    val nameLiveData = MutableLiveData<String>()
    val imageLiveData = MutableLiveData<Int>()
    fun imageLiveDataObserved(): LiveData<Int> = imageLiveData

    fun createCategory() {
        viewModelScope.launch {
            interactor.setCategory(
                Expense(
                    null,
                    nameLiveData.value ?: "",
                    null,
                    imageLiveData.value ?: R.drawable.home,
                    R.color.color_2
                )
            )
        }
    }


    fun setImage(imageSrc: Int) {
        imageLiveData.postValue(imageSrc)
    }

    fun setName(name: String) {
        nameLiveData.postValue(name)
    }
}