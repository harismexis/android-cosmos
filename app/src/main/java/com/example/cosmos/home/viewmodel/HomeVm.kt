package com.example.cosmos.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cosmos.workshared.enums.RowType

class HomeVm : ViewModel() {

    private val TAG = HomeVm::class.qualifiedName
    private var homeEntries: MutableList<RowType> = mutableListOf()

    init {
        initialiseHomeEntries()
    }

    private fun initialiseHomeEntries() {
        homeEntries.add(RowType.APOD)
        homeEntries.add(RowType.MRP)
    }

    fun getHomeEntries(): MutableList<RowType> {
        return homeEntries
    }

}