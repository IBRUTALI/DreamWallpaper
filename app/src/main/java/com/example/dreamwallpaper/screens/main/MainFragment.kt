package com.example.dreamwallpaper.screens.main

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.FragmentMainBinding
import androidx.annotation.NonNull
import java.util.*


class MainFragment : Fragment() {
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { MainAdapter() }

    private fun init() {
        val category: Array<String> = resources.getStringArray(R.array.category)
        val localeCategory: Array<String> = getLocalizedResources(requireContext(), Locale.ENGLISH).getStringArray(R.array.category)
        val icon: Array<Int> = arrayOf(
            R.drawable.ic_baseline_grass_24,
            R.drawable.ic_baseline_local_bar_24,
            R.drawable.ic_baseline_image_24,
            R.drawable.ic_baseline_science_24,
            R.drawable.ic_baseline_airplanemode_active_24,
            R.drawable.ic_baseline_pets_24,
            R.drawable.ic_baseline_sports_volleyball_24
        )
        binding.mainList.adapter = adapter
        binding.mainList.layoutManager = LinearLayoutManager(requireContext())
        adapter.setList(category, icon, localeCategory)
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

    private fun getLocalizedResources(context: Context, desiredLocale: Locale?): Resources {
        var conf: Configuration = context.resources.configuration
        conf = Configuration(conf)
        conf.setLocale(desiredLocale)
        val localizedContext: Context = context.createConfigurationContext(conf)
        return localizedContext.resources
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        fun clickCategory(string: String, view: View) {
            val bundle = Bundle()
            bundle.putString("category", string)
            view.findNavController().navigate(R.id.action_mainFragment_to_imageListFragment, bundle)
        }
    }
}