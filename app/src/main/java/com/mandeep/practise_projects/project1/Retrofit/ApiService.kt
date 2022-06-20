package com.mandeep.practise_projects.project1.Retrofit

import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Items
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET("/api/")
   suspend fun getData(@Query("key") key:String, @Query("page") page:Int, @Query("per_page") per_page:Int):Items
}