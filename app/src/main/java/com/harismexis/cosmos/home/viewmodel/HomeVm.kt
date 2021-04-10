package com.harismexis.cosmos.home.viewmodel

import androidx.lifecycle.ViewModel
import com.harismexis.cosmos.workshared.enums.RowType
import javax.inject.Inject

class HomeVm @Inject constructor() : ViewModel() {

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