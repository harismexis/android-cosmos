package com.example.cosmos.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmos.apod.activity.APODActivity.Companion.startAPODActivity
import com.example.cosmos.databinding.ActivityHomeBinding
import com.example.cosmos.home.adapter.HomeAdapter
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.home.viewmodel.HomeVm
import com.example.cosmos.mrp.activity.MRPActivity.Companion.startMRPActivity
import com.example.cosmos.workshared.enums.RowType

class HomeActivity : AppCompatActivity(), HomeClickListener {

    private lateinit var viewModel: HomeVm
    private lateinit var viewBinding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseViewBinding()
        setContentView(viewBinding.root)
        initialiseViewModel()
        initRecycler()
    }

    private fun initialiseViewBinding() {
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
    }

    private fun initialiseViewModel() {
        viewModel = HomeVm()
    }

    private fun initRecycler() {
        adapter = HomeAdapter(viewModel.getHomeEntries(), this)
        viewBinding.rvHome.layoutManager = LinearLayoutManager(this)
        viewBinding.rvHome.adapter = adapter
    }

    override fun onHomeItemClick(rowType: RowType, position: Int) {
        when (rowType) {
            RowType.APOD -> this.startAPODActivity()
            RowType.MRP -> this.startMRPActivity()
        }
    }

}