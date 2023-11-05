package com.example.delivery.presentation.menu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delivery.domain.model.Category
import com.example.delivery.domain.model.Result
import com.example.delivery.domain.usecase.GetCategoriesUseCase
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<Result<List<Category>>>()
    val categories: LiveData<Result<List<Category>>>
        get() = _categories

    init {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                _categories.value = result
            }
        }
    }
}