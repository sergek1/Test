package com.example.delivery.domain.usecase

import com.example.delivery.domain.model.Category
import com.example.delivery.domain.model.Result
import com.example.delivery.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val repository: MealsRepository) {
    suspend operator fun invoke(): Flow<Result<List<Category>>> {
        return repository.getCategories()
    }
}