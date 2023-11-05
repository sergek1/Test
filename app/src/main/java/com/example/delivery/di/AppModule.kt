package com.example.delivery.di

import com.example.delivery.presentation.menu.viewmodel.CategoriesViewModel
import com.example.delivery.presentation.menu.viewmodel.MealsByCategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        CategoriesViewModel(
            getCategoriesUseCase = get()
        )
    }

    viewModel {
        MealsByCategoryViewModel(
            getMealsByCategoryUseCase = get()
        )
    }
}