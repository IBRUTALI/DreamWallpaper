package com.example.dreamwallpaper.screens.image_list

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.dreamwallpaper.MAIN
import com.example.dreamwallpaper.databinding.ItemImageBinding
import com.example.dreamwallpaper.domain.models.Hit

class ImageListAdapter: RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private var imageList = emptyList<Hit>()

    class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        with(holder.binding) {
            val requestListener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemProgress.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemProgress.visibility = View.GONE
                    return false
                }

            }

            Glide.with(MAIN)
                .load(imageList[position].webformatURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(null)
                .listener(requestListener)
                .into(itemImage)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Hit>) {
            imageList = list
            notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ImageViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {view ->
            ImageListFragment.clickImage(imageList[holder.adapterPosition], view)
        }
    }

    override fun onViewDetachedFromWindow(holder: ImageViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

}