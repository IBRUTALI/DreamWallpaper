package com.example.dreamwallpaper.screens.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamwallpaper.R
import kotlinx.android.synthetic.main.item_category.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var categoryList: Array<String>
    private lateinit var categoryIconList: Array<Int>
    private lateinit var categoryRetrofitList: Array<String>

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.apply {
            category_title_item.text = categoryList[position]
            image_view_category_item.setImageResource(categoryIconList[position])
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