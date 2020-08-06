package com.mishaismenska.android_2020_task_6.presenters

import com.mishaismenska.android_2020_task_6.interfaces.DataManager
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailView
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailsPresenter
import javax.inject.Inject

class VideosDetailsPresenterImpl @Inject constructor(
    private val dataManager: DataManager
) : VideosDetailsPresenter {

    private var view: VideosDetailView? = null

    override fun bindToView(
        view: VideosDetailView
    ) {
        this.view = view
    }

    override fun loadVideo(position: Int) {
        val video = dataManager.getVideoAt(position)
        view?.showVideo(video)
    }

    override fun clearUp() {
        view = null
    }
}
