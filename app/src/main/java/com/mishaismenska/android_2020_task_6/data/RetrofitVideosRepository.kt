package com.mishaismenska.android_2020_task_6.data

import com.mishaismenska.android_2020_task_6.interfaces.RetrofitVideosService
import com.mishaismenska.android_2020_task_6.interfaces.VideosRepository
import javax.inject.Inject

class RetrofitVideosRepository
@Inject constructor(private val retrofitVideosService: RetrofitVideosService) : VideosRepository {

    private fun XmlVideo.toVideoMeta() = VideoMeta(
        image.url,
        title.replace("&quot;", "\""),
        description,
        duration,
        group.thumbnail.url,
        enclosure.url
    )

    private fun XmlData.toVideosMetaList() = this.channel.item.map {
        it.toVideoMeta()
    }

    override suspend fun getVideos(): List<VideoMeta> {
        val videosXmlData = retrofitVideosService.getVideos()
        return videosXmlData.toVideosMetaList()
    }
}
