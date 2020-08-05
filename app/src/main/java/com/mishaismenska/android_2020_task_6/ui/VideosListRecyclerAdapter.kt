package com.mishaismenska.android_2020_task_6.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mishaismenska.android_2020_task_6.databinding.VideoRecyclerItemBinding
import com.mishaismenska.android_2020_task_6.interfaces.VideosListPresenter

class VideosListRecyclerAdapter(private val presenter: VideosListPresenter) :
    RecyclerView.Adapter<VideosListRecyclerAdapter.VideoViwHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViwHolder =
        presenter.createViewHolder(parent, viewType)

    override fun getItemCount(): Int = presenter.getItemsCount()

    override fun onBindViewHolder(holder: VideoViwHolder, position: Int) {
        presenter.bind(holder, position)
    }

    class VideoViwHolder(val binding: VideoRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
