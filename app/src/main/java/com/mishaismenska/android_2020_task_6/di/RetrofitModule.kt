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
    fun provideTickXmlBuilder(): TikXml.Builder = TikXml.Builder()

    @Provides
    @Singleton
    fun provideTickXml(tickXmlBuilder: TikXml.Builder): TikXml =
        tickXmlBuilder.exceptionOnUnreadXml(false).build()

    @Provides
    @Singleton
    fun provideTickXmlConverterFactory(tickXml: TikXml): TikXmlConverterFactory = TikXmlConverterFactory.create(tickXml)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideRetrofit(tickXmlConverterFactory: TikXmlConverterFactory, retrofitBuilder: Retrofit.Builder): Retrofit {
        return retrofitBuilder
            .addConverterFactory(tickXmlConverterFactory)
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
