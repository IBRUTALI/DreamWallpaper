package com.example.dreamwallpaper.screens.image

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.models.Hit

class ImageFullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_fullscreen)
//        val bundle = Bundle()
//        bundle.putSerializable("image", intent.getSerializableExtra("image") as Hit)
//        val imageFullscreenFragment = ImageFullscreenFragment()
//        imageFullscreenFragment.arguments = bundle
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add(R.id.fullscreen_container, ImageFullscreenFragment())
//        }
    }

}