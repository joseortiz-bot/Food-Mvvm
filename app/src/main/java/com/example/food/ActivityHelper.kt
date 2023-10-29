package com.example.food

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food.data.local.database.FakeDatabase
import com.example.food.databinding.ActivityMainBinding
import com.example.food.domain.entities.FoodEntity
import com.example.food.presentation.adapters.FoodAdapter
import com.example.food.repositories.FoodDataSource
import com.google.android.material.textfield.TextInputLayout

object ActivityHelper {
    fun setupRecyclerView(recyclerView: RecyclerView, thisAdapter: FoodAdapter, llm: RecyclerView.LayoutManager) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = llm
            adapter = thisAdapter
        }
    }

    fun setupAutoComplete(
        context: Context,
        tilType: TextInputLayout,
        fakeDatabase: FakeDatabase
    ) {
        val types = fakeDatabase.getAllTypes().map { it.name }
        val typeAdapter = ArrayAdapter(context, R.layout.simple_dropdown_item_1line, types)
        (tilType.editText as? AutoCompleteTextView)?.setAdapter(typeAdapter)
    }

    fun setupButtons(
        binding: ActivityMainBinding,
        fakeDatabase: FakeDatabase,
        dataSource: FoodDataSource,
        adapter: FoodAdapter,
        clearForm: () -> Unit,
        saveFood: (FoodEntity) -> Unit,
        getFoods: () -> Unit
    ) {
        with(binding) {
            btnSave.setOnClickListener {
                val type = fakeDatabase.getAllTypes().first { it.name == acType.text.toString() }.id
                val food = FoodEntity(
                    price = etPrice.text.toString().toDouble(),
                    name = etName.text.toString(),
                    type = type
                )
                saveFood(food)
            }
            btnClear.setOnClickListener { clearForm() }
        }
    }
}
