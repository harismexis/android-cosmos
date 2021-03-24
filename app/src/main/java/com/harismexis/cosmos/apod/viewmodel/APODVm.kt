package com.harismexis.cosmos.apod.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import com.harismexis.cosmos.workshared.util.network.ConnectivityMonitor
import com.harismexis.cosmos.apod.model.APOD
import com.harismexis.cosmos.apod.repository.APODRepo
import kotlinx.coroutines.launch

class APODVm(
    var apodRepo: APODRepo,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = APODVm::class.qualifiedName

    private val mApod = MutableLiveData<APOD>()
    val apod: LiveData<APOD>
        get() = mApod

    init {
        fetchAPODToday()
    }

    private fun fetchAPODToday() {
        viewModelScope.launch {
            try {
                mApod.value = apodRepo.getAPODToday()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    fun fetchAPODByDate(date: String) {
        viewModelScope.launch {
            try {
                mApod.value = apodRepo.getAPODByDate(date)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}