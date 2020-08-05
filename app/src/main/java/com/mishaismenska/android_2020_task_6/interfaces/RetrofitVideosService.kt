package com.mishaismenska.android_2020_task_6.interfaces

import com.mishaismenska.android_2020_task_6.AppConstants
import com.mishaismenska.android_2020_task_6.data.XmlData
import javax.inject.Singleton
import retrofit2.http.GET

@Singleton
interface RetrofitVideosService {
    @GET(AppConstants.TED_RSS_DATA_PATH)
    suspend fun getVideos(): XmlData
}
