package com.example.food

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodApp : Application() {
//    companion object{
//        lateinit var database: FoodDataBase
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        database =  Room.databaseBuilder(
//            this,
//            FoodDataBase::class.java,
//            "FoodDataBase"
//        )
//            .build()
//    }

}