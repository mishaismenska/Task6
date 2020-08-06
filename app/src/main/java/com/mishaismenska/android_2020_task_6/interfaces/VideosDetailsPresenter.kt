package com.mishaismenska.android_2020_task_6.interfaces

interface VideosDetailsPresenter {
    fun bindToView(view: VideosDetailView) {}
    fun clearUp() {}
    fun startVideo() {}
    fun stopVideo() {}
    fun loadVideo(position: Int) {}
}
