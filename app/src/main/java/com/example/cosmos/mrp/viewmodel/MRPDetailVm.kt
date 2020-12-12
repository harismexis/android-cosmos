package com.example.cosmos.mrp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cosmos.mrp.repository.MRPRepo
import com.example.cosmos.workshared.util.network.ConnectivityMonitor

class MRPDetailVm(
    var marsRepo: MRPRepo,
    var connectivity: ConnectivityMonitor,
): ViewModel() {


}