package com.mishaismenska.android_2020_task_6.ui

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.mishaismenska.android_2020_task_6.App
import com.mishaismenska.android_2020_task_6.AppConstants
import com.mishaismenska.android_2020_task_6.data.VideoMeta
import com.mishaismenska.android_2020_task_6.databinding.FragmentDetailsBinding
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailView
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailsPresenter
import javax.inject.Inject

class DetailsFragment : Fragment(), VideosDetailView {

    @Inject
    lateinit var presenter: VideosDetailsPresenter
    private lateinit var binding: FragmentDetailsBinding
    private var videoPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (requireActivity().application as App).appComponent.inject(this)
        presenter.bindToView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        adjustActionBar()
        val position = arguments?.getInt(AppConstants.POSITION_KEY) ?: 0
        presenter.loadVideo(position)
        return binding.root
    }

    private fun adjustActionBar() {
        val mainActivity = activity as MainActivity
        if (binding.root.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            mainActivity.supportActionBar?.hide()
        else mainActivity.supportActionBar?.show()
    }

    override fun showVideo(video: VideoMeta) {
        if (binding.titleTextView != null)
            binding.titleTextView!!.text = video.title
        if (binding.descriptionTextView != null)
            binding.descriptionTextView!!.text = video.description
        binding.tedVideoView.setVideoURI(Uri.parse(video.enclosureUrl))
        binding.tedVideoView.setMediaController(MediaController(binding.tedVideoViewContainer.context))
        binding.tedVideoView.start()
    }

    override fun onPause() {
        super.onPause()
        videoPosition = binding.tedVideoView.currentPosition
    }

    override fun onResume() {
        super.onResume()
        binding.tedVideoView.seekTo(videoPosition)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearUp()
    }
}
