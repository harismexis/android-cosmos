package com.harismexis.cosmos.home.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.cosmos.apod.activity.APODActivity.Companion.startAPODActivity
import com.harismexis.cosmos.databinding.ActivityHomeBinding
import com.harismexis.cosmos.home.adapter.HomeAdapter
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewmodel.HomeVm
import com.harismexis.cosmos.mrp.activity.MRPActivity.Companion.startMRPActivity
import com.harismexis.cosmos.workshared.activity.BaseActivity
import com.harismexis.cosmos.workshared.enums.RowType

class HomeActivity : BaseActivity(), HomeClickListener {

    private val viewModel: HomeVm by viewModels { viewModelFactory }
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecycler()
    }

    override fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun getToolbar(): Toolbar {
        return binding.homeToolbar
    }

    override fun onHomeItemClick(rowType: RowType, position: Int) {
        when (rowType) {
            RowType.APOD -> this.startAPODActivity()
            RowType.MRP -> this.startMRPActivity()
        }
    }

    private fun setupRecycler() {
        adapter = HomeAdapter(viewModel.getHomeEntries(), this)
        binding.rvHome.layoutManager = LinearLayoutManager(this)
        binding.rvHome.adapter = adapter
    }

}