package com.mandeep.practise_projects.project1.di

import android.content.Context
import androidx.room.Room
import com.mandeep.practise_projects.project1.Retrofit.ApiService
import com.mandeep.practise_projects.project1.Room.DaoService
import com.mandeep.practise_projects.project1.Room.MyRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Module1 {

    val BASE_URL = "https://pixabay.com"
    val databaseName = "PixabayItemsDatabase"


    @Provides
    @Singleton
    fun provideDao(): ApiService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRoomDao(@ApplicationContext context: Context):DaoService = Room.databaseBuilder(context,MyRoom::class.java,databaseName).fallbackToDestructiveMigration().build().getDaoService()

}