package com.example.delivery.data.repository

import com.example.delivery.data.local.MealDao
import com.example.delivery.data.mapper.asDomainModel
import com.example.delivery.data.remote.MealsApi
import com.example.delivery.data.remote.dto.CategoryListDto
import com.example.delivery.data.remote.dto.MealsByCategoryListDto
import com.example.delivery.domain.model.Banner
import com.example.delivery.domain.model.Category
import com.example.delivery.domain.model.Meal
import com.example.delivery.domain.model.Result
import com.example.delivery.domain.repository.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MealsRepositoryImpl(
    private val mealsApi: MealsApi,
    private val mealDao: MealDao
) : MealsRepository {

    override suspend fun getCategories(): Flow<Result<List<Category>>> = flow {
        emit(Result.Loading())
        val categoryListDto = try {
            suspendCoroutine<CategoryListDto?> { continuation ->
                val call = mealsApi.getCategories()
                call.enqueue(object : Callback<CategoryListDto> {
                    override fun onResponse(
                        call: Call<CategoryListDto>,
                        response: Response<CategoryListDto>
                    ) {
                        if (response.isSuccessful) {
                            val categoryListDto = response.body()
                            continuation.resume(categoryListDto)
                        } else {
                            continuation.resumeWithException(Throwable("Failed with code: ${response.code()}"))
                        }
                    }

                    override fun onFailure(call: Call<CategoryListDto>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }
        } catch (e: Exception) {
            null
        }

        if (categoryListDto != null) {
            emit(Result.Success(categoryListDto.asDomainModel()))
        } else {
            emit(Result.Failure("Request failed"))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun getMealsByCategory(categoryName: String): Flow<Result<List<Meal>>> = flow {
        emit(Result.Loading())
        val mealsByCategoryListDto = try {
            suspendCoroutine<MealsByCategoryListDto?> { continuation ->
                val call = mealsApi.getMealsByCategory(categoryName)
                call.enqueue(object : Callback<MealsByCategoryListDto> {
                    override fun onResponse(
                        call: Call<MealsByCategoryListDto>,
                        response: Response<MealsByCategoryListDto>
                    ) {
                        if (response.isSuccessful) {
                            val mealsByCategoryListDto = response.body()
                            continuation.resume(mealsByCategoryListDto)
                        } else {
                            continuation.resumeWithException(Throwable("Failed with code: ${response.code()}"))
                        }
                    }

                    override fun onFailure(call: Call<MealsByCategoryListDto>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }
        } catch (e: Exception) {
            null
        }

        if (mealsByCategoryListDto != null) {
            emit(Result.Success(mealsByCategoryListDto.asDomainModel()))
        } else {
            emit(Result.Failure("Request failed"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFavorites(): Result<List<Meal>> {
        return Result.Loading()
    }

    override suspend fun getMealsByCategoryCache(categoryName: String): Result<List<Meal>> {
        return Result.Loading()
    }

    override suspend fun getBanners(): Result<List<Banner>> {
        return Result.Loading()
    }
}