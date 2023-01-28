package com.example.dreamwallpaper.screens.image_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dreamwallpaper.MAIN
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.databinding.FragmentImageListBinding
import com.example.dreamwallpaper.models.Hit

class ImageListFragment : Fragment() {
    private var mBinding: FragmentImageListBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { ImageListAdapter() }
    private lateinit var currentCategory: String
    private val viewModel: ImageListFragmentViewModel by viewModels()
    private var page = 1

    private fun init() {
        binding.imageListRv.adapter = adapter
        binding.imageListRv.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.getImagesByCategory(currentCategory, page)
        viewModel.state.observe(viewLifecycleOwner, { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.imageList.observe(viewLifecycleOwner, { list ->
            try {
            adapter.setList(list.body()!!.hits)
                binding.imageListPage.text = page.toString()
                if (page > 1) binding.btnBack.visibility = VISIBLE
                else binding.btnBack.visibility = INVISIBLE
        }catch (e: NullPointerException) {}
        })

        binding.btnBack.setOnClickListener {
            --page
            viewModel.getImagesByCategory(currentCategory, page)
        }

        binding.btnForward.setOnClickListener {
            ++page
            viewModel.getImagesByCategory(currentCategory, page)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
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
