package com.example.dreamwallpaper.screens.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dreamwallpaper.MAIN
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import java.util.regex.Pattern

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

    fun MaterialToolbar.setTitle(label: CharSequence?, textView: TextView, arguments: Bundle?) {
        if (label != null) {
            // Fill in the data pattern with the args to build a valid URI
            val title = StringBuffer()
            val fillInPattern = Pattern.compile("\\{(.+?)\\}")
            val matcher = fillInPattern.matcher(label)
            while (matcher.find()) {
                val argName = matcher.group(1)
                if (arguments != null && arguments.containsKey(argName)) {
                    matcher.appendReplacement(title, "")
                    title.append(arguments.get(argName).toString())
                } else {
                    return //returning because the argument required is not found
                }
            }
            matcher.appendTail(title)
            setTitle("")
            textView.text = title
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