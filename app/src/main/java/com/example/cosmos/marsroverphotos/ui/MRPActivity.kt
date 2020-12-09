package com.example.cosmos.marsroverphotos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmos.databinding.ActivityMrpBinding
import com.example.cosmos.marsroverphotos.model.response.MarsPhoto
import com.example.cosmos.marsroverphotos.model.ui.MRPUiModel
import com.example.cosmos.marsroverphotos.repository.MRPRepository
import com.example.cosmos.marsroverphotos.viewmodel.MRPViewModel
import com.example.cosmos.wshared.network.ConnectivityMonitor
import com.example.cosmos.wshared.network.ConnectivityRequestProvider

class MRPActivity : AppCompatActivity() {

    private lateinit var viewModel: MRPViewModel
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
        viewModel = MRPViewModel(
            MRPRepository(),
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