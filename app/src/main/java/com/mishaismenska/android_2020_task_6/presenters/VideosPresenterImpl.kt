package com.mishaismenska.android_2020_task_6.presenters

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.mishaismenska.android_2020_task_6.AppConstants
import com.mishaismenska.android_2020_task_6.R
import com.mishaismenska.android_2020_task_6.databinding.FragmentVideosListBinding
import com.mishaismenska.android_2020_task_6.databinding.VideoRecyclerItemBinding
import com.mishaismenska.android_2020_task_6.interfaces.DataManager
import com.mishaismenska.android_2020_task_6.interfaces.OnVideoItemClickListener
import com.mishaismenska.android_2020_task_6.interfaces.VideosListPresenter
import com.mishaismenska.android_2020_task_6.interfaces.VideosListView
import com.mishaismenska.android_2020_task_6.ui.DetailsFragment
import com.mishaismenska.android_2020_task_6.ui.MainActivity
import com.mishaismenska.android_2020_task_6.ui.SettingsFragment
import com.mishaismenska.android_2020_task_6.ui.VideosListRecyclerAdapter
import javax.inject.Inject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VideosPresenterImpl @Inject constructor(
    private val dataManager: DataManager,
    val context: Context
) : VideosListPresenter, OnVideoItemClickListener {
    private var job: Job? = null
    private var videosListView: VideosListView? = null
    private var currentRepositoryType = "0"

    override fun bindToView(
        view: VideosListView,
        binding: FragmentVideosListBinding,
        activity: MainActivity
    ) {
        videosListView = view
        binding.mainRecycler.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        val context = binding.root.context
        decoration.setDrawable(
            context.resources.getDrawable(
                R.drawable.recycler_divider,
                context.theme
            )
        )
        activity.supportActionBar?.show()
        binding.mainRecycler.visibility = View.INVISIBLE
        binding.videosListProgressBar.visibility = View.VISIBLE
        binding.mainRecycler.addItemDecoration(decoration)
        job = GlobalScope.launch {
            val actualRepositoryType = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.data_source_key), "0")!!
            val forceReload = actualRepositoryType != currentRepositoryType
            currentRepositoryType = actualRepositoryType
            val videos = dataManager.getVideos(actualRepositoryType, forceReload)
            Handler(context.mainLooper).post {
                binding.mainRecycler.visibility = View.VISIBLE
                binding.videosListProgressBar.visibility = View.GONE
                binding.mainRecycler.adapter = VideosListRecyclerAdapter(this@VideosPresenterImpl)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideosListRecyclerAdapter.VideoViwHolder {
        val binding =
            VideoRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideosListRecyclerAdapter.VideoViwHolder(binding)
    }

    override fun getItemsCount(): Int =
        dataManager.getVideosCount()

    override fun bind(holder: VideosListRecyclerAdapter.VideoViwHolder, position: Int) {
        holder.binding.run {
            videoTitle.text = dataManager.getVideoAt(position).title
            videoDescription.text = dataManager.getVideoAt(position).description
            videoDuration.text = dataManager.getVideoAt(position).duration
            videoPreview.load(dataManager.getVideoAt(position).thumbnailUrl)
            root.setOnClickListener {
                onClick(position)
            }
        }
    }

    override fun cleanup() {
        job?.cancel()
    }

    override fun openSettings() {
        videosListView?.getSupportFragmentManager()?.beginTransaction()
            ?.replace(R.id.main_fragment_container, SettingsFragment())
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)?.addToBackStack(null)
            ?.commit()
    }

    override fun onClick(position: Int) {
        if (videosListView != null)
            videosListView!!.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(AppConstants.POSITION_KEY, position)
                    }
                }).addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }
}
