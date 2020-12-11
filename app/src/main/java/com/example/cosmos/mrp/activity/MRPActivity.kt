package com.example.cosmos.mrp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmos.databinding.ActivityMrpBinding
import com.example.cosmos.mrp.adapter.MRPAdapter
import com.example.cosmos.mrp.model.response.MRPItem
import com.example.cosmos.mrp.model.ui.MRPUiModel
import com.example.cosmos.mrp.repository.MRPRepo
import com.example.cosmos.mrp.viewmodel.MRPVm
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import com.example.cosmos.workshared.util.network.ConnectivityRequestProvider

class MRPActivity : AppCompatActivity() {

    private lateinit var viewModel: MRPVm
    private lateinit var viewBinding: ActivityMrpBinding
    private lateinit var adapter: MRPAdapter
    private var marsPhotos: MutableList<MRPItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseViewBinding()
        setContentView(viewBinding.root)
        initRecycler()
        initialiseViewModel()
        observeLiveData()
        viewModel.getCuriosityLatestPhotos()
    }

    private fun initialiseViewBinding() {
        viewBinding = ActivityMrpBinding.inflate(layoutInflater)
    }

    private fun initRecycler() {
        adapter = MRPAdapter(marsPhotos)
        viewBinding.rvMarsPhotos.layoutManager = LinearLayoutManager(this)
        viewBinding.rvMarsPhotos.adapter = adapter
    }

    private fun initialiseViewModel() {
        viewModel = MRPVm(
            MRPRepo(),
            ConnectivityMonitor(applicationContext, ConnectivityRequestProvider())
        )
    }

    private fun observeLiveData() {
        viewModel.marsPhotosUiModel.observe(this, {
            updateUI(it)
        })
    }

    private fun updateUI(uiModel: MRPUiModel) {
        marsPhotos.clear()
        marsPhotos.addAll(uiModel.photos)
        adapter.notifyDataSetChanged()
    }

}