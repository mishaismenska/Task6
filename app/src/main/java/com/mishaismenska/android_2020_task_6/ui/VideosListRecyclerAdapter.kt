package com.mishaismenska.android_2020_task_6.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mishaismenska.android_2020_task_6.data.VideoMeta
import com.mishaismenska.android_2020_task_6.databinding.VideoRecyclerItemBinding
import com.mishaismenska.android_2020_task_6.interfaces.OnVideoItemClickListener

class VideosListRecyclerAdapter(private val onVideoItemClickListener: OnVideoItemClickListener) :
    RecyclerView.Adapter<VideosListRecyclerAdapter.VideoViwHolder>() {

    var data = emptyList<VideoMeta>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViwHolder {
        val binding = VideoRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViwHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: VideoViwHolder, position: Int) {
        holder.bind(data[position], onVideoItemClickListener, position)
    }

    class VideoViwHolder(private val binding: VideoRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(videoMeta: VideoMeta, onVideoItemClickListener: OnVideoItemClickListener, position: Int) {
            binding.run {
                videoTitle.text = videoMeta.title
                videoDescription.text = videoMeta.description
                videoDuration.text = videoMeta.duration
                videoPreview.load(videoMeta.thumbnailUrl)
                root.setOnClickListener {
                    onVideoItemClickListener.onClick(position)
                }
            }
        }
    }
}
