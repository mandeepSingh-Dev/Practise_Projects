package com.mandeep.practise_projects.project1.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mandeep.practise_projects.databinding.FragmentWallpapersBinding
import com.mandeep.practise_projects.project1.MVVM.MyViewModel
import com.mandeep.practise_projects.project1.Paging_for_retrofit.MyPagingDataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Wallpapers_Fragment : Fragment() {

    lateinit var binding:FragmentWallpapersBinding
    val myViewModel:MyViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentWallpapersBinding.inflate(inflater,container,false)
        Log.d("fe9if","dofmdf")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myPagingDataAdapter = MyPagingDataAdapter(requireContext(),requireActivity())

        lifecycleScope.launch {
            myViewModel.listHit.collect {
                myPagingDataAdapter.submitData(it)
            }
        }

        val gridLayoutManager = GridLayoutManager(requireContext(),3)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.WallpaperRecyclerView.layoutManager = staggeredGridLayoutManager
        binding.WallpaperRecyclerView.adapter = myPagingDataAdapter


    }



}