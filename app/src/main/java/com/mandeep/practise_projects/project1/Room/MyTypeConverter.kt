package com.mandeep.practise_projects.project1.Room

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit


class MyTypeConverter {

    @TypeConverter
    fun getHit(hit: Hit):String{
        return Gson().toJson(hit)
    }
    @TypeConverter
    fun getString(string:String):Hit{
       return  Gson().fromJson(string,Hit::class.java)
    }
}