package com.example.dreamwallpaper.screens.image_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dreamwallpaper.MAIN
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.FragmentImageListBinding
import com.example.dreamwallpaper.models.Hit
import kotlin.properties.Delegates

class ImageListFragment : Fragment() {
    private var mBinding: FragmentImageListBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { ImageListAdapter() }
    private lateinit var currentCategory: String
    private var currentPage by Delegates.notNull<Int>()
    private val viewModel: ImageListFragmentViewModel by viewModels()
    private var imagesList: List<Hit> ?= null

    private fun loadImages() {
        binding.imageListRv.adapter = adapter
        binding.imageListRv.layoutManager = GridLayoutManager(requireContext(), 2)

        if (viewModel.imageList.value == null)
            viewModel.getImagesByCategory(currentCategory, currentPage)

        viewModel.pageLiveData.observe(viewLifecycleOwner, { page ->
            binding.imageListPage.text = page.toString()
            currentPage = page
            if (page > 1) binding.btnBack.visibility = VISIBLE
            else binding.btnBack.visibility = INVISIBLE
        })

        viewModel.state.observe(viewLifecycleOwner, { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.imageList.observe(viewLifecycleOwner, { list ->
            try {
                imagesList = list.body()!!.hits
                adapter.setList(imagesList!!)
            } catch (e: NullPointerException) {
            }
        })
    }

    private fun init() {
        binding.btnBack.setOnClickListener {
            MAIN.navController.popBackStack()
        }

        binding.btnForward.setOnClickListener {
            val nextPage = currentPage.plus(1)
            val id = MAIN.navController.currentDestination?.id
            val bundle = Bundle()
            bundle.putInt("page", nextPage)
            bundle.putString("category", currentCategory)
            MAIN.navController.navigate(id!!, bundle)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImages()
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentImageListBinding.inflate(layoutInflater, container, false)
        currentCategory = arguments?.getString("category") as String
        currentPage = arguments?.getInt("page") as Int
        if (currentPage == 0) currentPage = 1
        onBackPressed()
        return binding.root
    }

    private fun onBackPressed() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val id = MAIN.navController.currentDestination?.id
                    MAIN.navController.navigate(id!!)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        fun clickImage(model: Hit) {
            val bundle = Bundle()
            bundle.putSerializable("image", model)
            MAIN.navController.navigate(R.id.action_imageListFragment_to_imageFragment, bundle)
        }
    }
}
