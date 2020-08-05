package com.mishaismenska.android_2020_task_6.interfaces

import com.mishaismenska.android_2020_task_6.databinding.FragmentDetailsBinding
import com.mishaismenska.android_2020_task_6.ui.MainActivity

interface VideosDetailsPresenter {
    fun bindToView(
        view: VideosDetailView,
        binding: FragmentDetailsBinding,
        position: Int,
        activity: MainActivity
    )

    fun startVideo()
    fun stopVideo()
}
