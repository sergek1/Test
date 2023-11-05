package com.example.delivery.di

import androidx.room.Room
import com.example.delivery.data.local.MealDatabase
import com.example.delivery.data.remote.MealsApi
import com.example.delivery.data.repository.MealsRepositoryImpl
import com.example.delivery.domain.repository.MealsRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(MealsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealsApi::class.java)
    }

    single {
        Room.databaseBuilder(get(), MealDatabase::class.java, "meal_database").build()
    }

    single {
        get<MealDatabase>().mealDao()
    }

    single {
        MealsRepositoryImpl(
            mealsApi = get(),
            mealDao = get()
        ) as MealsRepository
    }

}