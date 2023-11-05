package com.example.delivery.presentation.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.delivery.databinding.ItemFoodBinding
import com.example.delivery.domain.model.Meal

class MealsByCategoryAdapter : RecyclerView.Adapter<MealsByCategoryAdapter.MealViewHolder>() {
    private var mealList = listOf<Meal>()

    fun updateData(newMealsList: List<Meal>) {
        val diffCallback = MealDiffCallback(mealList, newMealsList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mealList = newMealsList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding =
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    inner class MealViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) = with(binding) {
            tvFoodName.text = meal.name
            Glide.with(this.root).load(meal.imageUrl).into(ivFood)
            tvDescription.text = meal.name
        }
    }
}

class MealDiffCallback(private val oldList: List<Meal>, private val newList: List<Meal>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}