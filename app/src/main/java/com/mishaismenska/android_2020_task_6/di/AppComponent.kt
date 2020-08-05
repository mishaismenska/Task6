package com.mishaismenska.android_2020_task_6.di

import android.content.Context
import com.mishaismenska.android_2020_task_6.ui.DetailsFragment
import com.mishaismenska.android_2020_task_6.ui.VideosListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [VideosListModule::class, RetrofitModule::class, VideosDetailsModule::class])
@Singleton
interface AppComponent {
    fun inject(videosListFragment: VideosListFragment)
    fun inject(detailsFragment: DetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
