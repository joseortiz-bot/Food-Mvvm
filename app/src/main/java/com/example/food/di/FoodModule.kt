package com.example.food.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import com.example.food.data.local.dao.FoodDao
import com.example.food.data.local.database.FoodDataBase
import com.example.food.repositories.FoodDataSource
import com.example.food.repositories.FoodDataSourceRoom
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule{
    @Singleton
    @Binds
    abstract fun bindDataSource(impl: FoodDataSourceRoom): FoodDataSource
}

@InstallIn(SingletonComponent::class)
@Module
object RoomModule{
    @Provides
    fun provideDao(database: FoodDataBase): FoodDao = database.foodDao()

    @Singleton
    @Provides
    fun provideDatabaseRoom(@ApplicationContext app: Context): FoodDataBase = Room
        .databaseBuilder(
            app,
            FoodDataBase::class.java,
            "FoodDatabase"
        ).build()
}

@InstallIn(SingletonComponent::class)
@Module
object AdapterModule {
    @Provides
    fun provideLayoutManager(@ApplicationContext context: Context): LayoutManager =
        LinearLayoutManager(context)
    //fun provideLayoutManager(@ApplicationContext context: Context): LayoutManager = GridLayoutManager(context, 2)
}