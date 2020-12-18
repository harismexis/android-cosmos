package com.example.cosmos.apod.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.cosmos.R
import com.example.cosmos.apod.model.APOD
import com.example.cosmos.apod.repository.APODRepo
import com.example.cosmos.apod.viewmodel.APODVm
import com.example.cosmos.databinding.ActivityApodBinding
import com.example.cosmos.databinding.ApodViewBinding
import com.example.cosmos.workshared.activity.BaseActivity
import com.example.cosmos.workshared.extensions.extractYouTubeVideoId
import com.example.cosmos.workshared.extensions.hasHdUrl
import com.example.cosmos.workshared.util.datepicker.showDatePicker
import com.example.cosmos.workshared.util.general.getAPODFormattedDate
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import com.example.cosmos.workshared.util.network.ConnectivityRequestProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class APODActivity : BaseActivity() {

    private lateinit var picker: DatePickerDialog
    private lateinit var viewModel: APODVm
    private lateinit var binding: ActivityApodBinding
    private lateinit var apodView: ApodViewBinding

    private var youTubePlayer: YouTubePlayer? = null

    companion object {
        fun Context.startAPODActivity() {
            startActivity(Intent(this, APODActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
        observeLiveData()
        initialiseYouTubeView()
    }

    override fun initialiseViewBinding() {
        binding = ActivityApodBinding.inflate(layoutInflater)
        apodView = binding.apodContainer
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun initialiseViewModel() {
        viewModel = APODVm(
            APODRepo(),
            ConnectivityMonitor(applicationContext, ConnectivityRequestProvider())
        )
    }

    override fun getToolbar(): Toolbar {
        return binding.apodToolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_apod_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.choose_date -> {
            showDatePicker(
                this, apodView.txtDate.text.toString()
            ) { year, month, day ->
                val date = getAPODFormattedDate(year, month, day)
                viewModel.fetchAPODByDate(date)
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun observeLiveData() {
        viewModel.apod.observe(this, {
            updateUI(it)
        })
    }

    private fun updateUI(apod: APOD) {
        populateMedia(apod)
        apodView.txtTitle.text = apod.title
        apodView.txtDate.text = apod.date
        apodView.txtExplanation.text = apod.explanation
        apodView.txtServiceVersion.text = apod.serviceVersion
        apodView.txtMediaType.text = apod.mediaType
    }

    private fun populateMedia(apod: APOD) {
        val hasHdUrl = apod.hasHdUrl()
        onPrePopulateMedia(hasHdUrl)
        if (hasHdUrl) {
            apod.hdurl?.let {
                populateImage(it)
            }
        } else {
            populateVideo(apod.url)
        }
    }

    private fun populateVideo(url: String?) {
        url.extractYouTubeVideoId()?.let { it ->
            populateYoutubeView(it)
        }
    }

    private fun initialiseYouTubeView() {
        lifecycle.addObserver(apodView.youTubeView)
        apodView.youTubeView
            .initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull player: YouTubePlayer) {
                    youTubePlayer = player
                }
            })
    }

    /**
     * Example url of video file:
     * https://www.youtube.com/embed/NuLuCeawQSo?rel=0
     */
    private fun populateYoutubeView(videoId: String) {
        youTubePlayer?.loadVideo(videoId, 0f)
        apodView.youTubeView.visibility = View.VISIBLE
        apodView.imgProgressContainer.visibility = View.GONE
        apodView.youTubeView
    }

    private fun populateImage(imgUrl: String) {
        showImageProgress(true)
        Glide.with(this)
            .asBitmap()
            .load(Uri.parse(imgUrl))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    showImageProgress(false)
                    apodView.imgApod.setImageBitmap(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })
    }

    private fun showImageProgress(show: Boolean) {
        if (show) {
            apodView.imgApod.visibility = View.GONE
            apodView.imgProgressContainer.visibility = View.VISIBLE
        } else {
            apodView.imgApod.visibility = View.VISIBLE
            apodView.imgProgressContainer.visibility = View.GONE
        }
    }

    private fun onPrePopulateMedia(isImage: Boolean) {
        if (isImage) {
            youTubePlayer?.pause()
            apodView.youTubeView.visibility = View.GONE
        } else {
            apodView.youTubeView.visibility = View.VISIBLE
            hideImageView()
        }
    }

    private fun hideImageView() {
        apodView.imgProgressContainer.visibility = View.GONE
        apodView.imgApod.visibility = View.GONE
    }

}
