package com.example.dreamwallpaper.screens.image_list

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.dreamwallpaper.MAIN
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.models.Hit
import kotlinx.android.synthetic.main.item_image.view.*
import java.lang.NullPointerException

class ImageListAdapter: RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private var imageList = emptyList<Hit>()

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.itemView.apply {
            val requestListener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    item_progress.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    item_progress.visibility = View.GONE
                    return false
                }

            }

            Glide.with(MAIN)
                .load(imageList[position].webformatURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(null)
                .listener(requestListener)
                .into(item_image)
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
        holder.itemView.setOnClickListener {
            ImageListFragment.clickImage(imageList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ImageViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

}