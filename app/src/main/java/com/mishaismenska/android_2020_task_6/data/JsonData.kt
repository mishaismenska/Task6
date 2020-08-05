package com.mishaismenska.android_2020_task_6.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonData(
    val version: String,
    val channel: JsonChannel
)

@JsonClass(generateAdapter = true)
data class JsonChannel(val title: String, val item: List<JsonVideo>)

@JsonClass(generateAdapter = true)
class JsonVideo(
    val title: String,
    val image: JsonImage,
    val description: String,
    val duration: JsonDuration,
    val group: JsonGroup,
    val enclosure: JsonEnclosure
)

@JsonClass(generateAdapter = true)
data class JsonImage(val url: String)

@JsonClass(generateAdapter = true)
data class JsonDuration(val text: String)

@JsonClass(generateAdapter = true)
data class JsonGroup(val thumbnail: JsonThumbnail)

@JsonClass(generateAdapter = true)
data class JsonThumbnail(val url: String, val width: String, val height: String)

@JsonClass(generateAdapter = true)
data class JsonEnclosure(val url: String)
