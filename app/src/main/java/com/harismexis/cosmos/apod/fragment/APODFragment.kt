package com.harismexis.cosmos.apod.fragment

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.harismexis.cosmos.R
import com.harismexis.cosmos.apod.model.APOD
import com.harismexis.cosmos.apod.viewmodel.APODVm
import com.harismexis.cosmos.databinding.ApodViewBinding
import com.harismexis.cosmos.databinding.FragmentApodBinding
import com.harismexis.cosmos.workshared.activity.BaseFragment
import com.harismexis.cosmos.workshared.extensions.extractYouTubeVideoId
import com.harismexis.cosmos.workshared.extensions.hasHdUrl
import com.harismexis.cosmos.workshared.util.datepicker.showDatePicker
import com.harismexis.cosmos.workshared.util.general.getAPODFormattedDate
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class APODFragment : BaseFragment() {

    private val viewModel: APODVm by viewModels { viewModelFactory }
    private var binding: FragmentApodBinding? = null
    private var apodView: ApodViewBinding? = null

    private var youTubePlayer: YouTubePlayer? = null

    override fun onViewCreated() {

    }

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentApodBinding.inflate(layoutInflater)
        apodView = binding?.apodContainer
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    override fun observeLiveData() {
        viewModel.apod.observe(this, {
            updateUI(it)
        })
    }

    override fun initialiseView() {
        setupToolbar()
        observeLiveData()
        initialiseYouTubeView()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.let { it ->
            binding?.toolbar?.setupWithNavController(navController, appBarConf)
            binding?.toolbar?.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
            // it.toolbarTitle.text = getString(R.string.screen_home_label)
            it.toolbar.inflateMenu(R.menu.menu_apod_activity)
            it.toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.choose_date -> {
                        showDatePicker(
                            requireContext(), apodView?.txtDate?.text.toString()
                        ) { year, month, day ->
                            val date = getAPODFormattedDate(year, month, day)
                            viewModel.fetchAPODByDate(date)
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun updateUI(apod: APOD) {
        populateMedia(apod)
        apodView?.let {
            it.txtTitle.text = apod.title
            it.txtDate.text = apod.date
            it.txtExplanation.text = apod.explanation
            it.txtServiceVersion.text = apod.serviceVersion
            it.txtMediaType.text = apod.mediaType
        }
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
        apodView?.youTubeView?.let {
            lifecycle.addObserver(it)
            it.initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull player: YouTubePlayer) {
                    youTubePlayer = player
                }
            })
        }
    }

    /**
     * Example url of video file:
     * https://www.youtube.com/embed/NuLuCeawQSo?rel=0
     */
    private fun populateYoutubeView(videoId: String) {
        youTubePlayer?.loadVideo(videoId, 0f)
        apodView?.apply {
            youTubeView.visibility = View.VISIBLE
            imgProgressContainer.visibility = View.GONE
        }
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
                    apodView?.imgApod?.setImageBitmap(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })
    }

    private fun showImageProgress(show: Boolean) {
        if (show) {
            apodView?.imgApod?.visibility = View.GONE
            apodView?.imgProgressContainer?.visibility = View.VISIBLE
        } else {
            apodView?.imgApod?.visibility = View.VISIBLE
            apodView?.imgProgressContainer?.visibility = View.GONE
        }
    }

    private fun onPrePopulateMedia(isImage: Boolean) {
        if (isImage) {
            youTubePlayer?.pause()
            apodView?.youTubeView?.visibility = View.GONE
        } else {
            apodView?.youTubeView?.visibility = View.VISIBLE
            hideImageView()
        }
    }

    private fun hideImageView() {
        apodView?.imgProgressContainer?.visibility = View.GONE
        apodView?.imgApod?.visibility = View.GONE
    }

}
