package com.example.dreamwallpaper.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { MainAdapter() }

    private fun init() {
        binding.mainList.adapter = adapter
        binding.mainList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        const val CATEGORY = "category"

        fun clickCategory(string: String, view: View) {
            val bundle = Bundle()
            bundle.putString(CATEGORY, string)
            view.findNavController().navigate(R.id.action_mainFragment_to_imageListFragment, bundle)
        }
    }
}