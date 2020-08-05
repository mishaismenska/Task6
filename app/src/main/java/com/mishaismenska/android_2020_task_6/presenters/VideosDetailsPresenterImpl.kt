package com.mishaismenska.android_2020_task_6.presenters

import android.content.res.Configuration
import android.net.Uri
import android.widget.MediaController
import com.mishaismenska.android_2020_task_6.databinding.FragmentDetailsBinding
import com.mishaismenska.android_2020_task_6.interfaces.DataManager
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailView
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailsPresenter
import com.mishaismenska.android_2020_task_6.ui.MainActivity
import javax.inject.Inject

class VideosDetailsPresenterImpl @Inject constructor(
    private val dataManager: DataManager
) : VideosDetailsPresenter {

    private lateinit var view: VideosDetailView
    private lateinit var binding: FragmentDetailsBinding
    private var videoPosition = 0

    override fun bindToView(
        view: VideosDetailView,
        binding: FragmentDetailsBinding,
        position: Int,
        activity: MainActivity
    ) {
        this.view = view
        this.binding = binding
        val video = dataManager.getVideoAt(position)
        if (binding.titleTextView != null)
            binding.titleTextView.text = video.title
        if (binding.descriptionTextView != null)
            binding.descriptionTextView.text = video.description
        binding.tedVideoView.setVideoURI(Uri.parse(video.enclosureUrl))
        binding.tedVideoView.setMediaController(MediaController(binding.tedVideoViewContainer.context))
        binding.tedVideoView.start()
        if (binding.root.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            activity.supportActionBar?.hide()
        else activity.supportActionBar?.show()
    }

    override fun startVideo() {
        binding.tedVideoView.seekTo(videoPosition)
    }

    override fun stopVideo() {
        videoPosition = binding.tedVideoView.currentPosition
    }
}
