package com.example.cosmos.mrp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmos.databinding.ActivityMrpBinding
import com.example.cosmos.mrp.adapter.MRPItemAdapter
import com.example.cosmos.mrp.model.ui.MRPItemModel
import com.example.cosmos.mrp.repository.MRPRepo
import com.example.cosmos.mrp.viewholder.MRPItemVh
import com.example.cosmos.mrp.viewmodel.MRPVm
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import com.example.cosmos.workshared.util.network.ConnectivityRequestProvider

/**
 * Activity showing a list of Mars Rover Photos (MRP)
 */
class MRPActivity : AppCompatActivity(), MRPItemVh.MRPItemClickListener {

    private lateinit var viewModel: MRPVm
    private lateinit var viewBinding: ActivityMrpBinding
    private lateinit var adapter: MRPItemAdapter
    private var mrpItems: MutableList<MRPItemModel> = mutableListOf()

    companion object {
        fun Context.startMRPActivity() {
            startActivity(Intent(this, MRPActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseViewBinding()
        setContentView(viewBinding.root)
        initRecycler()
        initialiseViewModel()
        observeLiveData()
        viewModel.fetchCuriosityLatestMRP()
    }

    private fun initialiseViewBinding() {
        viewBinding = ActivityMrpBinding.inflate(layoutInflater)
    }

    private fun initRecycler() {
        adapter = MRPItemAdapter(mrpItems, this)
        adapter.setHasStableIds(true)
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
        viewModel.models.observe(this, {
            updateUI(it)
        })
    }

    private fun updateUI(uiModel: List<MRPItemModel>) {
        mrpItems.clear()
        mrpItems.addAll(uiModel)
        adapter.notifyDataSetChanged()
    }

    override fun onMRPItemClick(
        item: MRPItemModel,
        position: Int
    ) {
        startMRPDetailActivity()
    }

}