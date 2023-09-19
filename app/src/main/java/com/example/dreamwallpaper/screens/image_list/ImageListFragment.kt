package com.example.dreamwallpaper.screens.image_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.data.retrofit.models.Hit
import com.example.dreamwallpaper.databinding.FragmentImageListBinding
import com.example.dreamwallpaper.screens.main.MainFragment.Companion.CATEGORY
import com.example.dreamwallpaper.util.Result
import com.example.dreamwallpaper.util.getAppComponent
import com.example.dreamwallpaper.util.lazyViewModel

class ImageListFragment : Fragment() {
    private var mBinding: FragmentImageListBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { ImageListAdapter() }
    lateinit var navController: NavController
    private val viewModel: ImageListFragmentViewModel by lazyViewModel {
        getAppComponent().imageListFragmentViewModel().create(
            getCurrentCategoryFromBundle(),
            getCurrentPageFromBundle()
        )
    }

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
        return binding.root
    }

    private fun setAdapter() {
        binding.imageRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.imageRecycler.adapter = adapter
    }

    private fun pageObserver() {
        viewModel.page.observe(viewLifecycleOwner) { page ->
            binding.currentPage.text = page.toString()
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
                    adapter.setList(result.data?.hits ?: emptyList())
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
        viewModel.getImagesByCategory(
            viewModel.category.value!!,
            viewModel.page.value
        )
    }

    private fun nextPage() {
        binding.nextPage.setOnClickListener {
            val nextPage = viewModel.page.value!! + 1
            val id = navController.currentDestination?.id!!
            val bundle = setBundleArguments(
                getCurrentCategoryFromBundle(),
                nextPage
            )
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


    private fun setBundleArguments(category: String, page: Int): Bundle {
        return Bundle().apply {
            putString(CATEGORY, category)
            putInt(PAGE, page)
        }
    }

    private fun getCurrentCategoryFromBundle(): String {
        return arguments?.getString(CATEGORY) as String
    }

    private fun getCurrentPageFromBundle(): Int? {
        return arguments?.getInt(PAGE)
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
