package com.mishaismenska.android_2020_task_6.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mishaismenska.android_2020_task_6.App
import com.mishaismenska.android_2020_task_6.R
import com.mishaismenska.android_2020_task_6.databinding.FragmentVideosListBinding
import com.mishaismenska.android_2020_task_6.interfaces.VideosListPresenter
import com.mishaismenska.android_2020_task_6.interfaces.VideosListView
import javax.inject.Inject

class VideosListFragment : Fragment(), VideosListView {

    @Inject
    lateinit var videosListPresenter: VideosListPresenter

    private lateinit var binding: FragmentVideosListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosListBinding.inflate(inflater, container, false)
        videosListPresenter.bindToView(this, binding, activity as MainActivity)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        videosListPresenter.cleanup()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.open_settings) {
            videosListPresenter.openSettings()
            true
        } else false
    }

    override fun getSupportFragmentManager(): FragmentManager = parentFragmentManager
}
