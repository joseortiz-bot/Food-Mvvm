package com.example.food.repositories

import com.example.food.domain.entities.FoodEntity


interface FoodDataSource {
    suspend fun getAllFood(callback: (List<FoodEntity>) -> Unit)
    suspend fun addFood(foodEntity: FoodEntity, callback: (Long) -> Unit)
}