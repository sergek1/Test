package com.example.delivery.presentation.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery.databinding.ItemBannerBinding

class BannersAdapter : RecyclerView.Adapter<BannersAdapter.ImageViewHolder>() {
    private var imageList = listOf<Int>()

    fun updateData(newImageList: List<Int>) {
        val diffCallback = ImageDiffCallback(imageList, newImageList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        imageList = newImageList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ImageViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) = with(binding) {
            ivBanner.setImageResource(image)
        }
    }
}

class ImageDiffCallback(private val oldList: List<Int>, private val newList: List<Int>) :
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