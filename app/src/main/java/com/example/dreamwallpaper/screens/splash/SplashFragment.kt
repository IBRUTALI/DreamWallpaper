package com.example.dreamwallpaper.screens.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment: Fragment() {
    private var mBinding: FragmentSplashBinding?= null
    private val binding get() = mBinding!!

    private fun init(view: View) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.splashHorizontalProgress.max = 20
            val value = 19
            ObjectAnimator.ofInt(binding.splashHorizontalProgress, "progress", value).setDuration(1000).start()
            delay(1000)
            view.findNavController().navigate(R.id.action_splashFragment_to_mainActivity)
            activity?.finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
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