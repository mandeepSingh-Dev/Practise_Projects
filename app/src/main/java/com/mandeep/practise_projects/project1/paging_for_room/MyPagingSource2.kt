package com.mandeep.practise_projects.project1.paging_for_room

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mandeep.practise_projects.project1.MVVM.MainRepositry
import com.mandeep.practise_projects.project1.Room.EntityItem

class MyPagingSource2( val mainRepositry: MainRepositry):PagingSource<Int,EntityItem>() {

    override fun getRefreshKey(state: PagingState<Int, EntityItem>): Int? {
       return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EntityItem> {

        try {
            val currentpage = params.key ?: 0
            val list = ArrayList<EntityItem>()

            val liveItems = mainRepositry.queryItems()
         //   liveItems.observe(lifecycleOwner, Observer {
                list.addAll(liveItems)
           // })

            val prevKey = if (currentpage == 0) null else currentpage - 1
            val nextKey = if (list.isEmpty()) null else currentpage + 1

            val page = LoadResult.Page(data = list, prevKey = prevKey, nextKey = nextKey)
            return page
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }


}