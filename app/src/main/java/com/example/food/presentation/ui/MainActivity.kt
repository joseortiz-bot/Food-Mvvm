package com.example.food.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.food.ActivityHelper
import com.example.food.R
import com.example.food.data.local.database.FakeDatabase
import com.example.food.databinding.ActivityMainBinding
import com.example.food.domain.entities.FoodEntity
import com.example.food.presentation.adapters.FoodAdapter
import com.example.food.repositories.FoodDataSource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var fakeDatabase: FakeDatabase
    @Inject lateinit var dataSource: FoodDataSource
    @Inject lateinit var adapter: FoodAdapter
    @Inject lateinit var llm: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ActivityHelper.setupRecyclerView(binding.recyclerView, adapter, llm)
        ActivityHelper.setupAutoComplete(this, binding.tilType, fakeDatabase)
        ActivityHelper.setupButtons(
            binding,
            fakeDatabase,
            dataSource,
            adapter,
            this::clearForm,
            this::saveFood,
            this::getFoods
        )
    }

    private fun saveFood(food: FoodEntity) {
        lifecycleScope.launch {
            dataSource.addFood(food) { id ->
                if (id < 1) {
                    Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_LONG).show()
                } else {
                    Log.i("ANT", "saveFood: Success!!!")
                    clearForm()
                    getFoods()
                }
            }
        }
    }

    private fun clearForm() {
        with(binding) {
            etPrice.setText("")
            etName.setText("")
            acType.setText("")
        }
    }

    private fun getFoods() {
        lifecycleScope.launch {
            dataSource.getAllFood { foods ->
                adapter.submitList(foods)
            }
        }
    }
}
