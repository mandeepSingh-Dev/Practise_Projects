package com.mandeep.practise_projects.project1.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mandeep.practise_projects.R
import com.mandeep.practise_projects.databinding.FragmentSavedWallBinding
import com.mandeep.practise_projects.project1.MVVM.MainRepositry
import com.mandeep.practise_projects.project1.MVVM.MyViewModel
import com.mandeep.practise_projects.project1.paging_for_room.MyPagingDataAdpater2
import com.mandeep.practise_projects.project1.paging_for_room.MyPagingSource2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SavedWall_Fragment : Fragment() {


    @Inject
    lateinit var mainRepositry:MainRepositry

    val myViewModel:MyViewModel by viewModels()

    lateinit var binding:FragmentSavedWallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSavedWallBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myPagingDataAdpater2 = MyPagingDataAdpater2(requireContext())


      /*  val listt =Pager(PagingConfig(pageSize = 10)){
            MyPagingSource2(requireActivity(),mainRepositry)
        }.flow.cachedIn(CoroutineScope(Dispatchers.Main))

        lifecycleScope.launch {
            listt.collect {
                myPagingDataAdpater2.submitData(it)
            }
        }*/


        lifecycleScope.launch {
            myViewModel.listEntity.collect {
                myPagingDataAdpater2.submitData(it)
            }
        }

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerViews.layoutManager = staggeredGridLayoutManager
        binding.recyclerViews.adapter = myPagingDataAdpater2


    }

}