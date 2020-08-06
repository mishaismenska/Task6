package com.mishaismenska.android_2020_task_6.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishaismenska.android_2020_task_6.App
import com.mishaismenska.android_2020_task_6.AppConstants
import com.mishaismenska.android_2020_task_6.R
import com.mishaismenska.android_2020_task_6.data.VideoMeta
import com.mishaismenska.android_2020_task_6.databinding.FragmentVideosListBinding
import com.mishaismenska.android_2020_task_6.interfaces.OnVideoItemClickListener
import com.mishaismenska.android_2020_task_6.interfaces.VideosListPresenter
import com.mishaismenska.android_2020_task_6.interfaces.VideosListView
import javax.inject.Inject

class VideosListFragment : Fragment(), VideosListView, OnVideoItemClickListener {

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
        videosListPresenter.bindToView(this)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.show()
        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decoration.setDrawable(
            requireContext().getDrawable(
                R.drawable.recycler_divider
            )!!
        )
        binding.mainRecycler.addItemDecoration(decoration)
        binding.mainRecycler.adapter = VideosListRecyclerAdapter(this)
        videosListPresenter.loadContent()
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

    override fun openSettings() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, SettingsFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    private fun getSettingsFragment(id: Int) = DetailsFragment().apply {
        arguments = Bundle().apply {
            putInt(AppConstants.POSITION_KEY, id)
        }
    }

    override fun openVideoDetails(id: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, getSettingsFragment(id))
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }

    override fun showProgressBar() {
        binding.mainRecycler.visibility = View.INVISIBLE
        binding.videosListProgressBar.visibility = View.VISIBLE
    }

    override fun showContent(videos: List<VideoMeta>) {
        binding.mainRecycler.visibility = View.VISIBLE
        binding.videosListProgressBar.visibility = View.GONE
        (binding.mainRecycler.adapter as VideosListRecyclerAdapter).data = videos
    }

    override fun onClick(position: Int) {
        videosListPresenter.onVideoItemClick(position)
    }
}
