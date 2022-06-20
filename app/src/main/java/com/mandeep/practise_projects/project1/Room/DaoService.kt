package com.mandeep.practise_projects.project1.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit


@Dao
interface  DaoService {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(hit: EntityItem)

    @Query("SELECT * FROM item")
     suspend fun queryItems():List<EntityItem>
}