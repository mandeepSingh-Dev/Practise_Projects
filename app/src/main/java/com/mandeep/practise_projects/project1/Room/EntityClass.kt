package com.mandeep.practise_projects.project1.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit


@Entity(tableName = "Item")
data class EntityItem(

    val name:String,
    val largeImageURL: String,
    val user: String,
    val views: Int,
    )
{

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}