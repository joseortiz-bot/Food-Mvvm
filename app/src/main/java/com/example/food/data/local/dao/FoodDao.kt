package com.example.food.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.food.domain.entities.FoodEntity

@Dao
interface FoodDao {
    @Query("SELECT * FROM FoodEntity")
    suspend fun getAllFood(): List<FoodEntity>

    @Insert
    suspend fun addFood(foodEntity: FoodEntity): Long
}