package com.mishaismenska.android_2020_task_6.data

import com.mishaismenska.android_2020_task_6.interfaces.DataManager
import com.mishaismenska.android_2020_task_6.interfaces.VideosRepository
import javax.inject.Inject
import javax.inject.Named

class DataManagerImpl @Inject constructor(
    @Named("retrofit_repository")
    private val retrofitVideosRepository: VideosRepository,
    @Named("asserts_repository") val assertsVideosRepository: VideosRepository
) :
    DataManager {

    private var videos: List<VideoMeta>? = null

    override suspend fun getVideos(repositoryType: String, forceReload: Boolean): List<VideoMeta> {
        if (videos == null || forceReload) {
            videos = choseVideosRepository(repositoryType).getVideos()
        }
        return videos!!
    }

    override fun getVideoAt(position: Int): VideoMeta {
        return videos!![position]
    }

    override fun getVideosCount(): Int {
        return videos?.size ?: 0
    }

    private fun choseVideosRepository(type: String): VideosRepository {
        return if (type == "0") retrofitVideosRepository
        else assertsVideosRepository
    }
}
