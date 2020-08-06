package com.mishaismenska.android_2020_task_6.interfaces

import com.mishaismenska.android_2020_task_6.data.VideoMeta

interface VideosListView {
    fun openSettings() {}
    fun openVideoDetails(id: Int) {}
    fun showProgressBar() {}
    fun showContent(videos: List<VideoMeta>) {}
}
