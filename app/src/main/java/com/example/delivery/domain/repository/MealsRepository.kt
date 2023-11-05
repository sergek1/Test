package com.example.delivery.domain.repository

import com.example.delivery.domain.model.Banner
import com.example.delivery.domain.model.Category
import com.example.delivery.domain.model.Meal
import com.example.delivery.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface MealsRepository {
    suspend fun getCategories(): Flow<Result<List<Category>>>
    suspend fun getMealsByCategory(categoryName: String): Flow<Result<List<Meal>>>
    suspend fun getFavorites(): Result<List<Meal>>
    suspend fun getMealsByCategoryCache(categoryName: String): Result<List<Meal>>
    suspend fun getBanners(): Result<List<Banner>>
}