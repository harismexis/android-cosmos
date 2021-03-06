package com.harismexis.cosmos.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.FragmentMediaPlayerBinding
import com.harismexis.cosmos.workshared.activity.BaseFragment

/**
 * Player for loading videos and audios from NASA Image and Video Library.
 * It is used for playing mp4 etc and it's based on Android MediaPlayer.
 */
class MediaPlayerFragment : BaseFragment(),
    SurfaceHolder.Callback,
    SeekBar.OnSeekBarChangeListener,
    MediaPlayer.OnPreparedListener {

    private val viewModel: MediaPlayerVm by viewModels { viewModelFactory }
    private var binding: FragmentMediaPlayerBinding? = null
    private val player = MediaPlayer()
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var seekRunnable: Runnable
    private var mediaUrl: String? = null

    companion object {
        const val ARG_MEDIA_COLLECTION_URL = "mediaCollectionUrl"
        const val SECOND = 1000
        const val SAMPLE_NASA_VIDEO_URL =
            "https://images-assets.nasa.gov/video/JSC-Orion-2021-GA_infographic_animation4k/JSC-Orion-2021-GA_infographic_animation4k~orig.mp4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mediaCollectionUrl = arguments?.getString(ARG_MEDIA_COLLECTION_URL)
        // TODO: Call viewModel.fetchMediaUrls(mediaCollectionUrl) to obtain the
        // video / audio url to load in the Player.
        // Currently just loading a fixed url of a sample video
    }

    override fun initialiseView() {}

    override fun onViewCreated() {
        player.setOnPreparedListener(this)
        binding?.let {
            it.playerView.holder.addCallback(this)
            it.seekBar.setOnSeekBarChangeListener(this)
            it.playBtn.isEnabled = false
            it.playBtn.isVisible = false
            it.playBtn.setOnClickListener { _ ->
                if (player.isPlaying) {
                    player.pause()
                    it.playBtn.setImageResource(R.drawable.ic_play_arrow_white_24dp)
                } else {
                    player.start()
                    it.playBtn.setImageResource(R.drawable.ic_pause_white_24dp)
                }
            }
        }
    }

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentMediaPlayerBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    private fun observeLiveData() {
        // TODO: Observe the live data from ViewModel to get the
        // url of the video / audio to load in the player
    }

    private fun initializeSeekBar() {
        binding?.let {
            it.seekBar.max = player.seconds
            it.txtProgress.text = getString(R.string.media_player_progress)
            it.txtDuration.text = formatTime(player.seconds)
            it.progressBar.visibility = View.GONE
            it.playBtn.isEnabled = true
            it.playBtn.isVisible = true
        }
    }

    private fun updateSeekBar() {
        seekRunnable = Runnable {
            binding?.let {
                it.txtProgress.text = formatTime(player.currentSeconds)
                it.seekBar.progress = player.currentSeconds
            }
            handler.postDelayed(seekRunnable, SECOND.toLong())
        }
        handler.postDelayed(seekRunnable, SECOND.toLong())
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        player.apply {
            // TODO: Load the real video url, currently loading a fixed url
            setDataSource(SAMPLE_NASA_VIDEO_URL)
            setDisplay(surfaceHolder)
            prepareAsync()
        }
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {}

    override fun onPrepared(mediaPlayer: MediaPlayer?) {
        initializeSeekBar()
        updateSeekBar()
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) player.seekTo(progress * SECOND)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    override fun onDestroyView() {
        super.onDestroyView()
        if (this::seekRunnable.isInitialized) {
            handler.removeCallbacks(seekRunnable)
        }
        player.release()
    }

    private fun formatTime(seconds: Int): String {
        return String.format(
            "%02d:%02d",
            (seconds / 3600 * 60 + ((seconds % 3600) / 60)),
            (seconds % 60)
        )
    }

    private val MediaPlayer.seconds: Int
        get() {
            return this.duration / SECOND
        }

    private val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / SECOND
        }
}