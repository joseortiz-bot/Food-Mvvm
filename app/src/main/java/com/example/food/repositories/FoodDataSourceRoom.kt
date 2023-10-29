package com.example.food.repositories

import com.example.food.data.local.dao.FoodDao
import com.example.food.domain.entities.FoodEntity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FoodDataSourceRoom @Inject constructor(private val dao: FoodDao) : FoodDataSource {
    override suspend fun getAllFood(callback: (List<FoodEntity>) -> Unit) {
        callback(dao.getAllFood())
    }

    override suspend fun addFood(foodEntity: FoodEntity, callback: (Long) -> Unit) {
        val result = dao.addFood(foodEntity)
        callback(result)
    }
}