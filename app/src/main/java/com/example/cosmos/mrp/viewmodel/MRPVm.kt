package com.example.cosmos.mrp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.mrp.extensions.toUiModel
import com.example.cosmos.mrp.model.ui.MRPItemModel
import com.example.cosmos.mrp.repository.MRPRepo
import com.example.cosmos.workshared.extensions.getErrorMessage
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import kotlinx.coroutines.launch

class MRPVm(
    var marsRepo: MRPRepo,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = MRPVm::class.qualifiedName

    private val mModels = MutableLiveData<List<MRPItemModel>>()
    val models: LiveData<List<MRPItemModel>>
        get() = mModels

    fun fetchCuriosityLatestMRP() {
        viewModelScope.launch {
            try {
                val response = marsRepo.getCuriosityLatestMRP()
                mModels.value = response.toUiModel().mrpItems
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}