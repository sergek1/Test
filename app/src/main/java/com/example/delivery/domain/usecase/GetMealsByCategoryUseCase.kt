package com.example.delivery.domain.usecase

import com.example.delivery.domain.model.Meal
import com.example.delivery.domain.model.Result
import com.example.delivery.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetMealsByCategoryUseCase(private val repository: MealsRepository) {
    suspend operator fun invoke(idCategory: String): Flow<Result<List<Meal>>> {
        return repository.getMealsByCategory(idCategory)
    }
}