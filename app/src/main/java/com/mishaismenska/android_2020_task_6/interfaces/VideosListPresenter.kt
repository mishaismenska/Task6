package com.mishaismenska.android_2020_task_6.interfaces

interface VideosListPresenter {
    fun bindToView(view: VideosListView)
    fun cleanup() {}
    fun openSettings() {}
    fun loadContent() {}
    fun onVideoItemClick(position: Int) {}
}
