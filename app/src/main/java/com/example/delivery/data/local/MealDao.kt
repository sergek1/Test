package com.example.delivery.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.delivery.data.local.model.MealEntity

@Dao
interface MealDao {

    @Query("SELECT * FROM table_meal WHERE category = :category")
    suspend fun getMealsByCategory(category: String): List<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: MealEntity)

}