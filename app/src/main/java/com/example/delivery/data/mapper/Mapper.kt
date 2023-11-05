package com.example.delivery.data.mapper

import com.example.delivery.data.remote.dto.CategoryListDto
import com.example.delivery.data.remote.dto.MealsByCategoryListDto
import com.example.delivery.domain.model.Category
import com.example.delivery.domain.model.Meal

fun CategoryListDto.asDomainModel(): List<Category> {
    return categories.map {
        Category(
            categoryId = it.idCategory,
            categoryName = it.strCategory,
        )
    }
}

fun MealsByCategoryListDto.asDomainModel(): List<Meal> {
    return meals.map {
        Meal(
            id = it.idMeal,
            name = it.strMeal,
            imageUrl = it.strMealThumb
        )
    }
}