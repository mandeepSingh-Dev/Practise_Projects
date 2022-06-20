package com.mandeep.practise_projects.project1.Room

import androidx.room.*


@Database (entities = [EntityItem::class], version = 2, exportSchema = false)
//@TypeConverters(MyTypeConverter::class)
abstract class MyRoom :RoomDatabase(){

    abstract  fun getDaoService():DaoService

}