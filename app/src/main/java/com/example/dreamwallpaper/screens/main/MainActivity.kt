package com.example.dreamwallpaper.screens.main

import android.os.Bundle
import android.view.Menu
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
        binding.toolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
            setSupportActionBar(this)
        }
        onDestinationChangedListener(appBarConfiguration)
        onCustomToolbarBackPress()

    }

    private fun onDestinationChangedListener(appBarConfiguration: AppBarConfiguration) {
        binding.toolbar.apply {
            navController.addOnDestinationChangedListener { _, destination, arg ->
                setTitle(destination.label, binding.toolbarTextView, arg)
                val isTopLevelDestination =
                    appBarConfiguration.topLevelDestinations.contains(destination.id)
                if (!isTopLevelDestination) {
                    setNavigationIcon(R.drawable.ic_back)
                    setNavigationIconTint(resources.getColor(R.color.orange))
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.mainFragment) {
            moveTaskToBack(true)
        } else {
            navController.popBackStack()
        }
    }

    private fun onCustomToolbarBackPress() {
        binding.toolbar.setNavigationOnClickListener {
            if (navController.currentDestination?.id == R.id.imageFullscreenFragment)
                navController.popBackStack()
            else navController.navigate(R.id.mainFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}