package com.example.dreamwallpaper.screens.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.ActivityMainBinding
import com.example.dreamwallpaper.util.setTitle

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        with(binding.toolbar) {
            setupWithNavController(navController, appBarConfiguration)
            navController.addOnDestinationChangedListener { _, destination, arg ->
                binding.toolbar.setTitle(destination.label, binding.toolbarTextView, arg)
                if (destination.id == R.id.imageFullscreenFragment) {
                    visibility = GONE
                    binding.collapsingToolbarLayout.visibility = GONE
                } else {
                    visibility = VISIBLE
                    binding.collapsingToolbarLayout.visibility = VISIBLE
                }
                val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination.id)
                if(!isTopLevelDestination){
                    setNavigationIcon(R.drawable.ic_back)
                    setNavigationIconTint(resources.getColor(R.color.orange_lite))
                }
            }
        }

    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.mainFragment) {
            finish()
        } else {
            navController.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}