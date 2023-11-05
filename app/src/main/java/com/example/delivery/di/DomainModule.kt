package com.example.delivery.di

import com.example.delivery.domain.usecase.GetCategoriesUseCase
import com.example.delivery.domain.usecase.GetMealsByCategoryUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCategoriesUseCase(repository = get()) }
    factory { GetMealsByCategoryUseCase(repository = get()) }
}