package com.example.cosmos.apod.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.apod.model.APODResponse
import com.example.cosmos.apod.repository.APODRepo
import com.example.cosmos.apod.viewmodel.APODVm
import com.example.cosmos.databinding.ActivityApodBinding
import com.example.cosmos.databinding.ApodViewBinding
import com.example.cosmos.workshared.network.ConnectivityMonitor
import com.example.cosmos.workshared.network.ConnectivityRequestProvider

class APODActivity : AppCompatActivity() {

    private lateinit var viewModel: APODVm
    private lateinit var binding: ActivityApodBinding
    private lateinit var apodBinding: ApodViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseViewBinding()
        setContentView(binding.root)
        initialiseViewModel()
        observeLiveData()
    }

    private fun initialiseViewBinding() {
        binding = ActivityApodBinding.inflate(layoutInflater)
        apodBinding = binding.apodContainer
    }

    private fun initialiseViewModel() {
        viewModel = APODVm(
            APODRepo(),
            ConnectivityMonitor(applicationContext, ConnectivityRequestProvider())
        )
    }

    private fun observeLiveData() {
        viewModel.apod.observe(this, {
            updateUI(it)
        })
    }

    private fun updateUI(apod: APODResponse) {
        apod.hdurl?.let {
            loadImage(it)
        }
        apodBinding.txtTitle.text = apod.title
        apodBinding.txtDate.text = apod.date
        apodBinding.txtExplanation.text = apod.explanation
        apodBinding.txtServiceVersion.text = apod.serviceVersion
        apodBinding.txtMediaType.text = apod.mediaType
    }

    private fun loadImage(imgUrl: String) {
        Glide.with(this)
            .load(Uri.parse(imgUrl))
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(ColorDrawable(Color.BLACK))
            .into(apodBinding.imgApod)
    }

}