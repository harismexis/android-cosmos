package com.harismexis.cosmos.mediaplayer

import android.media.MediaPlayer
import android.net.Uri
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

    private val mediaPlayer = MediaPlayer()
    private lateinit var runnable: Runnable
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var selectedVideoUri: Uri
    private var binding: FragmentMediaPlayerBinding? = null

    companion object {
        const val GET_VIDEO = 123
        const val SECOND = 1000
        const val URL =
            "https://res.cloudinary.com/dit0lwal4/video/upload/v1597756157/samples/elephants.mp4"
    }

    override fun onViewCreated() {
        mediaPlayer.setOnPreparedListener(this)
        binding?.let {
            it.videoView.holder.addCallback(this)
            it.seekBar.setOnSeekBarChangeListener(this)
            it.playButton.isEnabled = false
            it.playButton.setOnClickListener { _ ->
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    it.playButton.setImageResource(android.R.drawable.ic_media_play)
                } else {
                    mediaPlayer.start()
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

    override fun initialiseView() {}

    private fun timeInString(seconds: Int): String {
        return String.format(
            "%02d:%02d",
            (seconds / 3600 * 60 + ((seconds % 3600) / 60)),
            (seconds % 60)
        )
    }

    private fun initializeSeekBar() {
        binding?.let {
            it.seekBar.max = mediaPlayer.seconds
            it.textProgress.text = getString(R.string.default_value)
            it.textTotalTime.text = timeInString(mediaPlayer.seconds)
            it.progressBar.visibility = View.GONE
            it.playButton.isEnabled = true
        }
    }

    private fun updateSeekBar() {
        runnable = Runnable {
            binding?.let {
                it.textProgress.text = timeInString(mediaPlayer.currentSeconds)
                it.seekBar.progress = mediaPlayer.currentSeconds
            }
            handler.postDelayed(runnable, SECOND.toLong())
        }
        handler.postDelayed(runnable, SECOND.toLong())
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        mediaPlayer.apply {
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
        if (fromUser)
            mediaPlayer.seekTo(progress * SECOND)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        mediaPlayer.release()
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