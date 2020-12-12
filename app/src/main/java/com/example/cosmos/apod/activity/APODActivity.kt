package com.example.cosmos.apod.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.R
import com.example.cosmos.apod.model.APOD
import com.example.cosmos.apod.repository.APODRepo
import com.example.cosmos.apod.viewmodel.APODVm
import com.example.cosmos.databinding.ActivityApodBinding
import com.example.cosmos.databinding.ApodViewBinding
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import com.example.cosmos.workshared.util.network.ConnectivityRequestProvider
import java.util.*


class APODActivity : AppCompatActivity() {

    private lateinit var picker: DatePickerDialog
    private lateinit var viewModel: APODVm
    private lateinit var binding: ActivityApodBinding
    private lateinit var apodBinding: ApodViewBinding

    companion object {
        fun Context.startAPODActivity() {
            startActivity(Intent(this, APODActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseViewBinding()
        setContentView(binding.root)
        initialiseViewModel()
        observeLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.activity_apod_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.choose_date -> {
            showDatePicker()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        picker = DatePickerDialog(
            this@APODActivity,
            { _, yearOf, monthOfYear, dayOfMonth ->
                val date = yearOf.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth
                viewModel.fetchAPODByDate(date)
            },
            year,
            month,
            day
        )
        picker.show()
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

    private fun updateUI(apod: APOD) {
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
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate()
            .placeholder(ColorDrawable(Color.BLACK))
            .into(apodBinding.imgApod)
    }

}