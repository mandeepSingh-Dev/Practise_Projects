package com.mandeep.practise_projects.project1.Paging_for_retrofit

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mandeep.practise_projects.project1.MVVM.MainRepositry
import com.mandeep.practise_projects.project1.Retrofit.API_KEY
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit

class MyPagingSource(val mainRepositry: MainRepositry): PagingSource<Int, Hit>() {

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        try{
            val currentKey = params.key ?:1

            val hitsList = ArrayList<Hit>()
            val items = mainRepositry.getData(API_KEY.APIKEY,currentKey,params.loadSize)
            items.hits.let {
                hitsList.addAll(it)
                Log.d("4ign4g",it.size.toString()+"   dfkdnfd")
            }

            val prevKey = if(currentKey == 1) null else currentKey - 1
            val nextKey = if(hitsList.isEmpty()) null else currentKey + 1

            val page = LoadResult.Page(data = hitsList, prevKey = prevKey, nextKey = nextKey)

            return page
        }catch (e:Exception){
            return LoadResult.Error(e)
        }

    }
}