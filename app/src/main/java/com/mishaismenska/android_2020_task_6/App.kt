package com.mishaismenska.android_2020_task_6

import android.app.Application
import com.mishaismenska.android_2020_task_6.di.AppComponent
import com.mishaismenska.android_2020_task_6.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}
