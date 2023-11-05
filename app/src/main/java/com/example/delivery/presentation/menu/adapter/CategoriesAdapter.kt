package com.example.delivery.presentation.menu.adapter

import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery.R
import com.example.delivery.databinding.ItemCategoryBinding
import com.example.delivery.domain.model.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoryList = listOf<Category>()
    private var selectedItemPosition: Int = 0

    private var onItemClick: ((String) -> Unit)? = null

    fun updateData(newCategoryList: List<Category>) {
        val diffCallback = CategoryDiffCallback(categoryList, newCategoryList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        categoryList = newCategoryList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentPosition = holder.adapterPosition
        val category = categoryList[position]
        holder.bind(category)
        holder.itemView.apply {
            setOnClickListener {
                onItemClick?.let { category.categoryName?.let { it1 -> it(it1) } }
                selectedItemPosition = currentPosition
                notifyDataSetChanged()
            }
            if (selectedItemPosition == currentPosition) {
                val elevationInPixels = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    0f,
                    holder.itemView.context.resources.displayMetrics
                )
                holder.binding.cardView.cardElevation = elevationInPixels
                holder.binding.frame.background =
                    ColorDrawable(ContextCompat.getColor(context, R.color.pink_17FD3A69))
            } else {
                val elevationInPixels = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    6f,
                    holder.itemView.context.resources.displayMetrics
                )
                holder.binding.cardView.cardElevation = elevationInPixels
                holder.binding.frame.background =
                    ColorDrawable(ContextCompat.getColor(context, R.color.white))
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun onItemClickListener(listener: (String) -> Unit) {
        onItemClick = listener
    }

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) = with(binding) {
            tvCategory.text = category.categoryName

        }
    }
}

class CategoryDiffCallback(
    private val oldList: List<Category>,
    private val newList: List<Category>
) :
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