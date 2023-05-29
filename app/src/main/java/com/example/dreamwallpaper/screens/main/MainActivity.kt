package com.example.dreamwallpaper.screens.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dreamwallpaper.MAIN
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MAIN = this
        navController = Navigation.findNavController(this, R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.imageFullscreenFragment) {
                binding.toolbar.visibility = GONE
            } else {
                binding.toolbar.visibility = VISIBLE
            }
            val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination.id)
            if(!isTopLevelDestination)
            binding.toolbar.setNavigationIcon(R.drawable.ic_back)
            binding.toolbar.setNavigationIconTint(resources.getColor(R.color.orange_lite))
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.mainFragment) {
        } else {
            navController.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}