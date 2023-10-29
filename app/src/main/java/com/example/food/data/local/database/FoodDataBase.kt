package com.example.food.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.food.data.local.dao.FoodDao
import com.example.food.domain.entities.FoodEntity

@Database(entities = [FoodEntity::class], version = 1)
 abstract class FoodDataBase : RoomDatabase() {
     abstract fun foodDao(): FoodDao
}