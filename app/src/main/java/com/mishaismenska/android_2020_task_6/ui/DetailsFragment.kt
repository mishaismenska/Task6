package com.mishaismenska.android_2020_task_6.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mishaismenska.android_2020_task_6.App
import com.mishaismenska.android_2020_task_6.AppConstants
import com.mishaismenska.android_2020_task_6.databinding.FragmentDetailsBinding
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailView
import com.mishaismenska.android_2020_task_6.interfaces.VideosDetailsPresenter
import javax.inject.Inject

class DetailsFragment : Fragment(), VideosDetailView {

    @Inject
    lateinit var presenter: VideosDetailsPresenter
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val position = arguments?.getInt(AppConstants.POSITION_KEY) ?: 0
        presenter.bindToView(this, binding, position, activity as MainActivity)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        presenter.stopVideo()
    }

    override fun onResume() {
        super.onResume()
        presenter.startVideo()
    }
}
