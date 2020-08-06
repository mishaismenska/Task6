package com.mishaismenska.android_2020_task_6.presenters

import android.content.Context
import android.os.Handler
import androidx.preference.PreferenceManager
import com.mishaismenska.android_2020_task_6.R
import com.mishaismenska.android_2020_task_6.interfaces.DataManager
import com.mishaismenska.android_2020_task_6.interfaces.VideosListPresenter
import com.mishaismenska.android_2020_task_6.interfaces.VideosListView
import javax.inject.Inject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VideosPresenterImpl @Inject constructor(
    private val dataManager: DataManager,
    val context: Context
) : VideosListPresenter {
    private var job: Job? = null
    private var videosListView: VideosListView? = null
    private var currentRepositoryType = "0"

    override fun bindToView(view: VideosListView) {
        videosListView = view
    }

    private fun isReloadNeeded(): Boolean {
        val actualRepositoryType = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(context.getString(R.string.data_source_key), "0") ?: "0"
        val forceReload = actualRepositoryType != currentRepositoryType
        currentRepositoryType = actualRepositoryType
        return forceReload
    }

    override fun loadContent() {
        videosListView?.showProgressBar()
        job = GlobalScope.launch {
            isReloadNeeded()
            val videos = dataManager.getVideos(currentRepositoryType, isReloadNeeded())
            Handler(context.mainLooper).post {
                videosListView?.showContent(videos)
            }
        }
    }

    override fun openSettings() {
        videosListView?.openSettings()
    }

    override fun onVideoItemClick(position: Int) {
        videosListView?.openVideoDetails(position)
    }

    override fun cleanup() {
        job?.cancel()
        videosListView = null
    }
}
