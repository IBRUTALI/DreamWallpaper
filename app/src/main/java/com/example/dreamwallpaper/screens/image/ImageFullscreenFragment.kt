package com.example.dreamwallpaper.screens.image

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.data.retrofit.models.Hit
import com.example.dreamwallpaper.databinding.FragmentFullscreenImageBinding
import com.example.dreamwallpaper.domain.download.FileDownloadWorker
import com.example.dreamwallpaper.screens.image_list.ImageListFragment.Companion.IMAGE
import com.example.dreamwallpaper.util.showAlert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class ImageFullscreenFragment : Fragment() {
    private var mBinding: FragmentFullscreenImageBinding? = null
    private val binding get() = mBinding!!
    private lateinit var currentImage: Hit
    private lateinit var workManager: WorkManager
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private var bitmap: Bitmap? = null

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        registerForActivityResult()
        setWallpaper()
        downloadImage()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFullscreenImageBinding.inflate(layoutInflater, container, false)
        currentImage = arguments?.getSerializable(IMAGE) as Hit

        if (currentImage.imageWidth > currentImage.imageHeight) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        return binding.root
    }

    private fun downloadImage() {
        binding.btnDownloadWallpaper.setOnClickListener {
            showAlert(
                getString(R.string.download_image),
                getString(R.string.you_want_download_image)
            ) {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )

            }
        }
    }


    private fun setWallpaper() {
        binding.btnSetWallpaper.setOnClickListener {
            if (bitmap != null) {
                showAlert(
                    getString(R.string.set_wallpaper),
                    getString(R.string.set_image_as_wallpaper)
                ) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val wallpaperManager = WallpaperManager.getInstance(requireContext())
                        try {
                            wallpaperManager.setBitmap(bitmap)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    private fun registerForActivityResult() {
        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) {

                var isGranted = false
                it.forEach { b ->
                    isGranted = b.value
                }

                if (isGranted) {
                    initWorkManager()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.permission_not_granted), Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }

    private fun initWorkManager() {
        workManager = WorkManager.getInstance(requireContext())
        val data = Data.Builder()

        data.apply {
            putString(FileDownloadWorker.FileParams.KEY_FILE_NAME, "Image")
            putString(FileDownloadWorker.FileParams.KEY_FILE_URL, currentImage.largeImageURL)
            putString(FileDownloadWorker.FileParams.KEY_FILE_TYPE, "PNG")
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val fileDownloadWorker = OneTimeWorkRequestBuilder<FileDownloadWorker>()
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        workManager.enqueueUniqueWork(
            "oneFileDownloadWork_${System.currentTimeMillis()}",
            ExistingWorkPolicy.KEEP,
            fileDownloadWorker
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}