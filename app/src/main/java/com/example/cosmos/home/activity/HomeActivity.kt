package com.example.cosmos.home.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmos.apod.activity.APODActivity.Companion.startAPODActivity
import com.example.cosmos.databinding.ActivityHomeBinding
import com.example.cosmos.home.adapter.HomeAdapter
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.home.viewmodel.HomeVm
import com.example.cosmos.mrp.activity.MRPActivity.Companion.startMRPActivity
import com.example.cosmos.workshared.activity.BaseActivity
import com.example.cosmos.workshared.enums.RowType

class HomeActivity : BaseActivity(), HomeClickListener {

    private lateinit var viewModel: HomeVm
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

    override fun initialiseViewModel() {
        viewModel = HomeVm()
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