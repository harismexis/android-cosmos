package com.harismexis.cosmos.apod.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.NonNull
import androidx.navigation.fragment.findNavController
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.FragmentApodPlayerBinding
import com.harismexis.cosmos.workshared.activity.BaseFragment
import com.harismexis.cosmos.workshared.util.ui.hideSystemUI
import com.harismexis.cosmos.workshared.util.ui.showSystemUI
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class APODPlayerFragment : BaseFragment() {

    companion object {
        const val ARG_VIDEO_ID = "apodVideoId"
    }

    private var binding: FragmentApodPlayerBinding? = null
    private var videoPlayer: YouTubePlayer? = null
    private var isFullScreen: Boolean = false

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentApodPlayerBinding.inflate(inflater, container, false)
    }

    override fun initialiseView() {
        addBackNavigation()
        initFullScreen()
    }

    override fun onViewCreated() {
        arguments?.let {
            val videoUrl = it.getString(ARG_VIDEO_ID)
            videoUrl?.let { url ->
                loadVideo(url)
            }
        }
    }

    private fun loadVideo(videoId: String) {
        binding?.let {
            lifecycle.addObserver(it.youTubeView)
            it.youTubeView.initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    videoPlayer = youTubePlayer
                    videoPlayer?.loadVideo(videoId, 0f)
                }
            })
        }
    }

    private fun addBackNavigation() {
        binding?.let {
            val controls = it.youTubeView.findViewById<RelativeLayout>(R.id.controls_container)
            val backIcon = ImageView(context)
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
            )
            params.marginStart =
                resources.getDimensionPixelSize(R.dimen.player_back_icon_margin_start)
            params.topMargin = resources.getDimensionPixelSize(R.dimen.player_back_icon_margin_top)
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
            params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
            backIcon.layoutParams = params
            backIcon.setImageResource(R.drawable.ic_arrow_left_white_rounded_24dp)
            backIcon.setOnClickListener {
                findNavController().navigate(APODPlayerFragmentDirections.actionExit())
            }
            controls.addView(backIcon)
        }
    }

    private fun initFullScreen() {
        binding?.let {
            it.youTubeView.getPlayerUiController()
                .setFullScreenButtonClickListener {
                    isFullScreen = !isFullScreen
                    if (isFullScreen) requireActivity().hideSystemUI()
                    else requireActivity().showSystemUI()
                }
        }
    }

    override fun onDestroyView() {
        if (isFullScreen) requireActivity().showSystemUI()
        binding = null
        super.onDestroyView()
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    private fun observeLiveData() {}

}