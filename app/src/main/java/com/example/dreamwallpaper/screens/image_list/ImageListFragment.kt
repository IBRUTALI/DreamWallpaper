package com.example.dreamwallpaper.screens.image_list

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
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
    private val viewModel: ImageListFragmentViewModel by viewModels()
    private var listImages: ArrayList<Hit>? = null
    private var currentPage by Delegates.notNull<Int>()
    private var previousImagesList: List<Hit>? = null

    private fun loadImages(savedInstanceState: Bundle?) {
        binding.imageListRv.adapter = adapter
        binding.imageListRv.layoutManager = GridLayoutManager(requireContext(), 2)

            if(viewModel.pageLiveData.value == null)
                currentPage = 1
            if(viewModel.imageList.value == null)
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
                listImages = (list.body()!!.hits) as ArrayList<Hit>?
                adapter.setList(listImages as List<Hit>)
            } catch (e: NullPointerException) {
            }
        })
    }

    private fun init(savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener {
            val previousPage = currentPage.minus(1)
            viewModel.getImagesByCategory(currentCategory, previousPage)
            binding.imageListRv.scrollToPosition(1)
        }

        binding.btnForward.setOnClickListener {
            val nextPage = currentPage.plus(1)
            viewModel.getImagesByCategory(currentCategory, nextPage)
            binding.imageListRv.scrollToPosition(1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImages(savedInstanceState)
        init(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentImageListBinding.inflate(layoutInflater, container, false)
        currentCategory = arguments?.getString("category") as String
        return binding.root
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
