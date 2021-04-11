package com.harismexis.cosmos.mediaplayer

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.FragmentMediaPlayerBinding
import com.harismexis.cosmos.workshared.activity.BaseFragment

class MediaPlayerFragment : BaseFragment(),
    SurfaceHolder.Callback,
    SeekBar.OnSeekBarChangeListener,
    MediaPlayer.OnPreparedListener {

    private var binding: FragmentMediaPlayerBinding? = null
    private val player = MediaPlayer()
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var seekRunnable: Runnable

    companion object {
        const val SECOND = 1000
        const val URL =
            "https://images-assets.nasa.gov/video/JSC-Orion-2021-GA_infographic_animation4k/JSC-Orion-2021-GA_infographic_animation4k~orig.mp4"
    }

    override fun initialiseView() {}

    override fun onViewCreated() {
        player.setOnPreparedListener(this)
        binding?.let {
            it.videoView.holder.addCallback(this)
            it.seekBar.setOnSeekBarChangeListener(this)
            it.playButton.isEnabled = false
            it.playButton.setOnClickListener { _ ->
                if (player.isPlaying) {
                    player.pause()
                    it.playButton.setImageResource(android.R.drawable.ic_media_play)
                } else {
                    player.start()
                    it.playButton.setImageResource(android.R.drawable.ic_media_pause)
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

    override fun observeLiveData() {}

    private fun initializeSeekBar() {
        binding?.let {
            it.seekBar.max = player.seconds
            it.textProgress.text = getString(R.string.default_value)
            it.textTotalTime.text = timeInString(player.seconds)
            it.progressBar.visibility = View.GONE
            it.playButton.isEnabled = true
        }
    }

    private fun updateSeekBar() {
        seekRunnable = Runnable {
            binding?.let {
                it.textProgress.text = timeInString(player.currentSeconds)
                it.seekBar.progress = player.currentSeconds
            }
            handler.postDelayed(seekRunnable, SECOND.toLong())
        }
        handler.postDelayed(seekRunnable, SECOND.toLong())
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        player.apply {
            setDataSource(URL)
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