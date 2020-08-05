package com.mishaismenska.android_2020_task_6.data

import android.content.Context
import com.mishaismenska.android_2020_task_6.AppConstants
import com.mishaismenska.android_2020_task_6.interfaces.VideosRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class AssertsVideosRepository @Inject constructor(private val context: Context) : VideosRepository {

    override suspend fun getVideos() = readVideosJsonString().toJsonData().toVideoMetaList()

    private fun JsonVideo.toVideoMeta() = VideoMeta(
        image.url,
        title,
        description,
        duration.text,
        group.thumbnail.url,
        enclosure.url
    )

    private fun JsonData.toVideoMetaList() = this.channel.item.map {
        it.toVideoMeta()
    }

    private fun readVideosJsonString(): String {
        val assertsManager = context.assets
        val dataJsonInputStream = assertsManager.open(AppConstants.DATA_JSON_FILE_NAME)
        return convertStreamToString(dataJsonInputStream)
    }

    private fun String.toJsonData(): JsonData {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter<JsonData>(JsonData::class.java)
        return adapter.fromJson(this)!!
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            sb.append(line + "\n")
        }
        inputStream.close()
        return sb.toString()
    }
}
