package com.mandeep.practise_projects.project1.MVVM

import androidx.lifecycle.LiveData
import com.mandeep.practise_projects.project1.Retrofit.ApiService
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Items
import com.mandeep.practise_projects.project1.Room.DaoService
import com.mandeep.practise_projects.project1.Room.EntityItem
import retrofit2.http.Query
import javax.inject.Inject

class MainRepositry @Inject constructor(val apiService: ApiService, val daoService: DaoService) {

   suspend fun getData(key:String, page:Int,per_page:Int):Items = apiService.getData(key,page,per_page)

   suspend fun insertData(entityItem: EntityItem)
   {
      daoService.insertItem(entityItem)
   }

   suspend fun queryItems():List<EntityItem> = daoService.queryItems()


}