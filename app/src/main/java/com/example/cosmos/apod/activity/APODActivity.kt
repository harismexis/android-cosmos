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
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import com.example.cosmos.workshared.util.network.ConnectivityRequestProvider
import java.text.SimpleDateFormat
import java.util.*

class APODActivity : BaseActivity() {

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
        setupActionBar()
        observeLiveData()
    }

    override fun initialiseViewBinding() {
        binding = ActivityApodBinding.inflate(layoutInflater)
        apodBinding = binding.apodContainer
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
            showDatePicker()
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

    private fun getCalendar(): Calendar {
        val calendar: Calendar = Calendar.getInstance()
        if (!apodBinding.txtDate.text.isNullOrBlank()) {
            val format = SimpleDateFormat("yyyy-MM-dd")
            calendar.time = format.parse(apodBinding.txtDate.text.toString())
        }
        return calendar
    }

    private fun showDatePicker() {
        val calendar = getCalendar()
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
        picker.datePicker.maxDate = Calendar.getInstance().timeInMillis
        picker.show()
    }

    private fun updateUI(apod: APOD) {
        apod.url?.let {
            loadImage(it)
        }
        apodBinding.txtTitle.text = apod.title
        apodBinding.txtDate.text = apod.date
        apodBinding.txtExplanation.text = apod.explanation
        apodBinding.txtServiceVersion.text = apod.serviceVersion
        apodBinding.txtMediaType.text = apod.mediaType
        binding.apodContainer.root.visibility = View.VISIBLE
    }

    private fun loadImage(imgUrl: String) {
        apodBinding.imgApod.visibility = View.GONE
        apodBinding.imgApodProgressView.visibility = View.VISIBLE
        Glide.with(this)
            .asBitmap()
            .load(Uri.parse(imgUrl))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    apodBinding.imgApod.visibility = View.VISIBLE
                    apodBinding.imgApod.setImageBitmap(resource)
                    apodBinding.imgApodProgressView.visibility = View.GONE
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })

    }


}