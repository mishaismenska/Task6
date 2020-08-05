package com.mishaismenska.android_2020_task_6.di

import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailsPresenter
import com.mishaismenska.android_2020_task_6.presenters.VideosDetailsPresenterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface VideosDetailsModule {
    @Binds
    @Singleton
    fun provideVideosDetailsPresenter(presenter: VideosDetailsPresenterImpl): VideosDetailsPresenter
}
