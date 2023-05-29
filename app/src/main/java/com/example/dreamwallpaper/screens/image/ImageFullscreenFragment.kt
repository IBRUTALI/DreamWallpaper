package com.example.dreamwallpaper.screens.image

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.FragmentFullscreenImageBinding
import com.example.dreamwallpaper.domain.models.Hit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class ImageFullscreenFragment : Fragment() {
    private var mBinding: FragmentFullscreenImageBinding? = null
    private val binding get() = mBinding!!
    private lateinit var currentImage: Hit
    private var bitmap: Bitmap ?= null

    private fun init() {

        Glide.with(this)
            .asBitmap()
            .load(currentImage.largeImageURL)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    binding.fullscreenImage.setImageBitmap(resource)
                    bitmap = resource
                    binding.fullscreenProgress.visibility = View.GONE
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        binding.fullscreenImage.scaleType = ImageView.ScaleType.FIT_XY

        binding.btnSetWallpaper.setOnClickListener {
            if(bitmap != null) dialogSetWallpaper()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFullscreenImageBinding.inflate(layoutInflater, container, false)
        currentImage = arguments?.getSerializable("image") as Hit

        if (currentImage.imageWidth > currentImage.imageHeight) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        return binding.root
    }


    private fun dialogSetWallpaper() {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        builder.setTitle(getString(R.string.set_image_as_wallpaper))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    try {
                        wallpaperManager.setBitmap(bitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            .setNegativeButton(getString(R.string.no)) { _, _ ->}
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}