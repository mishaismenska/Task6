package com.mishaismenska.android_2020_task_6.interfaces

import com.mishaismenska.android_2020_task_6.data.VideoMeta

interface VideosRepository {
    suspend fun getVideos(): List<VideoMeta>
}
