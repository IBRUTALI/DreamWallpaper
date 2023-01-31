package com.example.dreamwallpaper.screens.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dreamwallpaper.MainActivity
import com.example.dreamwallpaper.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment: Fragment() {
    private var mBinding: FragmentSplashBinding?= null
    private val binding get() = mBinding!!

    private fun init() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}