package com.example.delivery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.delivery.data.local.model.MealEntity

@Database(entities = [MealEntity::class], version = 1)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

}