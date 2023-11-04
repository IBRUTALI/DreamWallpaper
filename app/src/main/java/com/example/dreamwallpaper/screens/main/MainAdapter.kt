package com.example.dreamwallpaper.screens.main

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.ItemCategoryBinding
import java.util.Locale

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var categoryList: Array<String>
    private lateinit var categoryRetrofitList: Array<String>
    private val categoryIconList = arrayOf(
        R.drawable.ic_baseline_grass_24,
        R.drawable.ic_baseline_local_bar_24,
        R.drawable.ic_baseline_image_24,
        R.drawable.ic_baseline_science_24,
        R.drawable.ic_baseline_airplanemode_active_24,
        R.drawable.ic_baseline_pets_24,
        R.drawable.ic_baseline_sports_volleyball_24
    )
    private val categoryBackgroundList = arrayOf(
        R.drawable.nature,
        R.drawable.fashion,
        R.drawable.background,
        R.drawable.science,
        R.drawable.place,
        R.drawable.animals,
        R.drawable.sport
    )

    class MainViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        categoryList = getLocalizedResources(parent.context, Locale.ENGLISH).getStringArray(R.array.category)
        categoryRetrofitList = parent.context.resources.getStringArray(R.array.category)
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        with(holder.binding) {
            categoryTitleItem.text = categoryList[position]
            imageViewCategoryItem.setImageResource(categoryIconList[position])
            Glide.with(holder.itemView.context)
                .load(categoryBackgroundList[position])
                .centerCrop()
                .into(cardImageView)
        }
    }

    override fun getItemCount(): Int {
        return categoryBackgroundList.size
    }

    private fun getLocalizedResources(context: Context, desiredLocale: Locale?): Resources {
        var conf: Configuration = context.resources.configuration
        conf = Configuration(conf)
        conf.setLocale(desiredLocale)
        val localizedContext: Context = context.createConfigurationContext(conf)
        return localizedContext.resources
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