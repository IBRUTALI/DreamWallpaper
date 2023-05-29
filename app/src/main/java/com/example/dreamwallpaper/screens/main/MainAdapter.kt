package com.example.dreamwallpaper.screens.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.ItemCategoryBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var categoryList: Array<String>
    private lateinit var categoryIconList: Array<Int>
    private lateinit var categoryRetrofitList: Array<String>

    class MainViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        with(holder.binding) {
            categoryTitleItem.text = categoryList[position]
            imageViewCategoryItem.setImageResource(categoryIconList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: Array<String>, iconList: Array<Int>, localeCategoryList: Array<String>) {
        categoryList = list
        categoryIconList = iconList
        categoryRetrofitList = localeCategoryList
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {view ->
            Log.d("!@#",categoryRetrofitList[holder.adapterPosition].lowercase())
            MainFragment.clickCategory(categoryRetrofitList[holder.adapterPosition].lowercase(), view)
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}