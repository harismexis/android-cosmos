package com.harismexis.cosmos.mrp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.cosmos.databinding.ActivityMrpBinding
import com.harismexis.cosmos.mrp.model.ui.MRPItemModel
import com.harismexis.cosmos.mrp.repository.MRPRepo
import com.harismexis.cosmos.mrp.viewholder.MRPItemVh
import com.harismexis.cosmos.mrp.viewmodel.MRPVm
import com.harismexis.cosmos.workshared.activity.BaseActivity
import com.harismexis.cosmos.workshared.util.network.ConnectivityMonitor
import com.harismexis.cosmos.workshared.util.network.ConnectivityRequestProvider
import com.harismexis.cosmos.mrp.activity.MRPDetailActivity.Companion.startMRPDetailActivity
import com.harismexis.cosmos.mrp.adapter.MRPItemAdapter

/**
 * Activity showing a list of Mars Rover Photos (MRP)
 */
class MRPActivity : BaseActivity(), MRPItemVh.MRPItemClickListener {

    private val viewModel: MRPVm by viewModels { viewModelFactory }
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

    override fun getToolbar(): androidx.appcompat.widget.Toolbar {
        return binding.mrpToolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initRecycler() {
        adapter = MRPItemAdapter(mrpItems, this)
        adapter.setHasStableIds(true)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
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
        this.startMRPDetailActivity()
    }

}