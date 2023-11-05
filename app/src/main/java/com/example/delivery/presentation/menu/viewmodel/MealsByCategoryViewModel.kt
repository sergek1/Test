package com.example.delivery.presentation.menu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delivery.domain.model.Meal
import com.example.delivery.domain.model.Result
import com.example.delivery.domain.usecase.GetMealsByCategoryUseCase
import kotlinx.coroutines.launch

class MealsByCategoryViewModel(
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel() {
    private val _mealsResult = MutableLiveData<Result<List<Meal>>>()
    val mealsResult: LiveData<Result<List<Meal>>>
        get() = _mealsResult

    fun getMealsByCategory(categoryName: String) {
        viewModelScope.launch {
            getMealsByCategoryUseCase(categoryName)
                .collect { result ->
                    _mealsResult.value = result
                }
        }
    }
}