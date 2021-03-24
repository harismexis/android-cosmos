package com.harismexis.cosmos.mrp.viewmodel

import androidx.lifecycle.ViewModel
import com.harismexis.cosmos.workshared.util.network.ConnectivityMonitor
import com.harismexis.cosmos.mrp.repository.MRPRepo

class MRPDetailVm(
    var marsRepo: MRPRepo,
    var connectivity: ConnectivityMonitor,
): ViewModel() {


}