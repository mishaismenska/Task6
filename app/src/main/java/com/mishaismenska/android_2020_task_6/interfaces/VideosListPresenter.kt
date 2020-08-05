package com.mishaismenska.android_2020_task_6.interfaces

import android.view.ViewGroup
import com.mishaismenska.android_2020_task_6.databinding.FragmentVideosListBinding
import com.mishaismenska.android_2020_task_6.ui.MainActivity
import com.mishaismenska.android_2020_task_6.ui.VideosListRecyclerAdapter

interface VideosListPresenter {
    fun bindToView(view: VideosListView, binding: FragmentVideosListBinding, activity: MainActivity)
    fun createViewHolder(parent: ViewGroup, viewType: Int): VideosListRecyclerAdapter.VideoViwHolder
    fun getItemsCount(): Int
    fun bind(holder: VideosListRecyclerAdapter.VideoViwHolder, position: Int)
    fun cleanup()
    fun openSettings()
}
