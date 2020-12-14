package com.example.cosmos.mrp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmos.databinding.ActivityMrpBinding
import com.example.cosmos.mrp.adapter.MRPItemAdapter
import com.example.cosmos.mrp.model.ui.MRPItemModel
import com.example.cosmos.mrp.repository.MRPRepo
import com.example.cosmos.mrp.viewholder.MRPItemVh
import com.example.cosmos.mrp.viewmodel.MRPVm
import com.example.cosmos.workshared.activity.BaseActivity
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import com.example.cosmos.workshared.util.network.ConnectivityRequestProvider

/**
 * Activity showing a list of Mars Rover Photos (MRP)
 */
class MRPActivity : BaseActivity(), MRPItemVh.MRPItemClickListener {

    private lateinit var viewModel: MRPVm
    private lateinit var binding: ActivityMrpBinding
    private lateinit var adapter: MRPItemAdapter
    private var mrpItems: MutableList<MRPItemModel> = mutableListOf()

    companion object {
        fun Context.startMRPActivity() {
            startActivity(Intent(this, MRPActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        viewModel.fetchCuriosityLatestMRP()
    }

    override fun initialiseViewBinding() {
        binding = ActivityMrpBinding.inflate(layoutInflater)
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun initialiseView() {
        super.initialiseView()
        initRecycler()
    }

    override fun initialiseViewModel() {
        viewModel = MRPVm(
            MRPRepo(),
            ConnectivityMonitor(applicationContext, ConnectivityRequestProvider())
        )
    }

    override fun getToolbar(): Toolbar {
        return binding.mrpToolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initRecycler() {
        adapter = MRPItemAdapter(mrpItems, this)
        adapter.setHasStableIds(true)
        binding.rvMarsPhotos.layoutManager = LinearLayoutManager(this)
        binding.rvMarsPhotos.adapter = adapter
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