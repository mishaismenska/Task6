package com.mishaismenska.android_2020_task_6.interfaces

import com.mishaismenska.android_2020_task_6.data.VideoMeta

interface DataManager {
    suspend fun getVideos(repositoryType: String, forceReload: Boolean): List<VideoMeta>
    fun getVideoAt(position: Int): VideoMeta
    fun getVideosCount(): Int
}
