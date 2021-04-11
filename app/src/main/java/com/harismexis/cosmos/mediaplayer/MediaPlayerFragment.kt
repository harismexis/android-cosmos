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
import androidx.fragment.app.viewModels
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.FragmentMediaPlayerBinding
import com.harismexis.cosmos.workshared.activity.BaseFragment

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
    }

    override fun initialiseView() {}

    override fun onViewCreated() {
        player.setOnPreparedListener(this)
        binding?.let {
            it.playerView.holder.addCallback(this)
            it.seekBar.setOnSeekBarChangeListener(this)
            it.playBtn.isEnabled = false
            it.playBtn.setOnClickListener { _ ->
                if (player.isPlaying) {
                    player.pause()
                    it.playBtn.setImageResource(android.R.drawable.ic_media_play)
                } else {
                    player.start()
                    it.playBtn.setImageResource(android.R.drawable.ic_media_pause)
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
//        viewModel.mediaUrls.observe(viewLifecycleOwner, {
//            // Get video urls here and load to player
//        })
    }

    private fun initializeSeekBar() {
        binding?.let {
            it.seekBar.max = player.seconds
            it.txtProgress.text = getString(R.string.media_player_progress)
            it.txtDuration.text = timeInString(player.seconds)
            it.progressBar.visibility = View.GONE
            it.playBtn.isEnabled = true
        }
    }

    private fun updateSeekBar() {
        seekRunnable = Runnable {
            binding?.let {
                it.txtProgress.text = timeInString(player.currentSeconds)
                it.seekBar.progress = player.currentSeconds
            }
            handler.postDelayed(seekRunnable, SECOND.toLong())
        }
        handler.postDelayed(seekRunnable, SECOND.toLong())
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        player.apply {
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

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(seekRunnable)
        player.release()
    }

    private fun timeInString(seconds: Int): String {
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