package com.mishaismenska.android_2020_task_6.di

import com.mishaismenska.android_2020_task_6.data.AssertsVideosRepository
import com.mishaismenska.android_2020_task_6.data.DataManagerImpl
import com.mishaismenska.android_2020_task_6.data.RetrofitVideosRepository
import com.mishaismenska.android_2020_task_6.interfaces.DataManager
import com.mishaismenska.android_2020_task_6.interfaces.VideosListPresenter
import com.mishaismenska.android_2020_task_6.interfaces.VideosRepository
import com.mishaismenska.android_2020_task_6.presenters.VideosPresenterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
interface VideosListModule {

    @Binds
    @Named("asserts_repository")
    @Singleton

    fun provideAssertsVideosRepository(assertsVideosRepository: AssertsVideosRepository): VideosRepository

    @Binds
    @Named("retrofit_repository")
    @Singleton
    fun provideRetrofitVideosRepository(retrofitVideosRepository: RetrofitVideosRepository): VideosRepository

    @Binds
    @Singleton
    fun provideDataManager(dataManagerImpl: DataManagerImpl): DataManager

    @Binds
    @Singleton
    fun provideVideosListPresenter(videosListPresenterImpl: VideosPresenterImpl): VideosListPresenter
}
