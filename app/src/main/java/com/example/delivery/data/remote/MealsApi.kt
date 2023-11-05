package com.example.delivery.data.remote

import com.example.delivery.data.remote.dto.CategoryListDto
import com.example.delivery.data.remote.dto.MealsByCategoryListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    @GET("categories.php")
    fun getCategories(): Call<CategoryListDto>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName: String): Call<MealsByCategoryListDto>

}