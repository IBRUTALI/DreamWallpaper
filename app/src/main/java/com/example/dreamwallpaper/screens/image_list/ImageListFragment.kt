package com.example.dreamwallpaper.screens.image_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.FragmentImageListBinding
import com.example.dreamwallpaper.data.retrofit.models.Hit
import com.example.dreamwallpaper.screens.main.MainFragment.Companion.CATEGORY
import kotlin.properties.Delegates
import com.example.dreamwallpaper.util.Result
import com.example.dreamwallpaper.util.getAppComponent

class ImageListFragment : Fragment() {
    private var mBinding: FragmentImageListBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { ImageListAdapter() }
    private lateinit var currentCategory: String
    private var currentPage by Delegates.notNull<Int>()
    private val viewModel: ImageListFragmentViewModel by viewModels {
        getAppComponent().viewModelsFactory()
    }
    private var imagesList: List<Hit>? = null
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        setAdapter()
        pageObserver()
        subscribeImages()
        previousPage()
        nextPage()
        updateList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentImageListBinding.inflate(layoutInflater, container, false)
        currentCategory = arguments?.getString(CATEGORY) as String
        currentPage = arguments?.getInt(PAGE) as Int
        if (currentPage == 0) currentPage = 1
        onBackPressed()
        return binding.root
    }

    private fun setAdapter() {
        binding.imageRecycler.layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
        binding.imageRecycler.adapter = adapter
    }

    private fun pageObserver() {
        viewModel.page.observe(viewLifecycleOwner) { page ->
            binding.currentPage.text = page.toString()
            currentPage = page
            if (page > 1) binding.previousPage.visibility = VISIBLE
            else binding.previousPage.visibility = INVISIBLE
        }
    }

    private fun subscribeImages() {
        viewModel.imageList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.imageListProgress.visibility = VISIBLE
                    binding.imageReconnect.visibility = GONE
                }
                is Result.Success -> {
                    binding.imageListProgress.visibility = GONE
                    binding.imageReconnect.visibility = GONE
                    imagesList = result.data?.hits
                    adapter.setList(imagesList ?: emptyList())
                }
                is Result.Error -> {
                    binding.imageListProgress.visibility = GONE
                    binding.imageReconnect.visibility = VISIBLE
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadImages() {
            viewModel.getImagesByCategory(currentCategory, currentPage)
    }

    private fun nextPage() {
        binding.nextPage.setOnClickListener {
            val nextPage = currentPage.plus(1)
            val id = navController.currentDestination?.id!!
            val bundle = Bundle()
            bundle.putInt(PAGE, nextPage)
            bundle.putString(CATEGORY, currentCategory)
            navController.navigate(id, bundle)
        }
    }

    private fun previousPage() {
        binding.previousPage.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun updateList() {
        binding.imageReconnect.setOnClickListener {
            loadImages()
        }
    }


    private fun onBackPressed() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.currentDestination?.let {
                        navController.navigate(id)
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        const val PAGE = "page"
        const val IMAGE = "image"

        fun clickImage(model: Hit, view: View) {
            val bundle = Bundle()
            bundle.putSerializable(IMAGE, model)
            view.findNavController()
                .navigate(R.id.action_imageListFragment_to_imageFullscreenFragment, bundle)
        }
    }
}
