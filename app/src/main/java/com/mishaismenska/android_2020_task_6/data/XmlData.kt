package com.mishaismenska.android_2020_task_6.data

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class XmlData(
    @Attribute
    val version: String,
    @Element(name = "channel")
    val channel: XmlChannel
)

@Xml
data class XmlChannel(
    @PropertyElement
    val title: String,
    @Element(name = "item")
    val item: List<XmlVideo>
)

@Xml
data class XmlVideo(
    @PropertyElement
    val title: String,
    @Element(name = "itunes:image")
    val image: XmlImage,
    @PropertyElement(name = "description")
    val description: String,
    @PropertyElement(name = "itunes:duration")
    val duration: String,
    @Element(name = "media:group")
    val group: XmlGroup,
    @Element(name = "enclosure")
    val enclosure: XmlEnclosure
)

@Xml
data class XmlImage(@Attribute val url: String)

@Xml
data class XmlGroup(@Element(name = "media:thumbnail") val thumbnail: XmlThumbnail)

@Xml
data class XmlThumbnail(
    @Attribute val url: String,
    @Attribute val width: String,
    @Attribute val height: String
)

@Xml
data class XmlEnclosure(
    @Attribute val url: String
)
