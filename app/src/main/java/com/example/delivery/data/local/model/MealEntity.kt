package com.example.delivery.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_meal")
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = null,
    val category: String? = null,
    val imageUrl: String? = null,
)
