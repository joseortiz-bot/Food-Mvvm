package com.example.food.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.food.R
import com.example.food.data.local.database.FakeDatabase
import com.example.food.databinding.ItemFoodBinding
import com.example.food.domain.entities.FoodEntity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class FoodAdapter @Inject constructor(@ActivityContext private val context: Context,
                                      private val fakeDatabase: FakeDatabase,
                                      diff: FoodDiff
) :
    ListAdapter<FoodEntity, RecyclerView.ViewHolder>(diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_food, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val food = getItem(position)

        with((holder as ViewHolder).binding){
            tvPrice.text = context.getString(R.string.item_food_price, food.price)
            tvName.text = food.name
            tvType.text = fakeDatabase.getAllTypes().first { it.id == food.type }.name
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemFoodBinding.bind(view)
    }
}