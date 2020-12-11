package com.example.cosmos.mrp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmos.databinding.ActivityMrpBinding
import com.example.cosmos.mrp.adapter.MarsPhotosAdapter
import com.example.cosmos.mrp.model.response.MarsPhoto
import com.example.cosmos.mrp.model.ui.MRPUiModel
import com.example.cosmos.mrp.repository.MRPRepo
import com.example.cosmos.mrp.viewmodel.MRPVm
import com.example.cosmos.workshared.network.ConnectivityMonitor
import com.example.cosmos.workshared.network.ConnectivityRequestProvider

class MRPActivity : AppCompatActivity() {

    private lateinit var viewModel: MRPVm
    private lateinit var viewBinding: ActivityMrpBinding
    private lateinit var adapter: MarsPhotosAdapter
    private var marsPhotos: MutableList<MarsPhoto> = mutableListOf()

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
        // enableAnimationOnRecycler(false)
        // uiModels.addAll(viewModel.getInitialUiModels())
        adapter = MarsPhotosAdapter(marsPhotos)
        // adapter.setHasStableIds(true)
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