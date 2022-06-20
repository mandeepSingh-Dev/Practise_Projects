package com.mandeep.practise_projects.project1.MVVM

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mandeep.practise_projects.project1.Paging_for_retrofit.MyPagingSource
import com.mandeep.practise_projects.project1.paging_for_room.MyPagingSource2
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor( val mainRepositry: MainRepositry):ViewModel() {

    val listHit = Pager(PagingConfig(pageSize = 10)){
        MyPagingSource(mainRepositry)
    }.flow.cachedIn(viewModelScope)


    val listEntity = Pager(PagingConfig(pageSize = 10))
    {
       MyPagingSource2(mainRepositry = mainRepositry)
    }.flow.cachedIn(viewModelScope)
   
}