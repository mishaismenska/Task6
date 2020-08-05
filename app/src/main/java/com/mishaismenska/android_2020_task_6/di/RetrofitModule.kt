package com.mishaismenska.android_2020_task_6.di

import com.mishaismenska.android_2020_task_6.AppConstants
import com.mishaismenska.android_2020_task_6.interfaces.RetrofitVideosService
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder().exceptionOnUnreadXml(false).build()
                )
            )
            .baseUrl(AppConstants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatsApiService(retrofit: Retrofit): RetrofitVideosService {
        return retrofit.create(
            RetrofitVideosService::class.java
        )
    }
}
