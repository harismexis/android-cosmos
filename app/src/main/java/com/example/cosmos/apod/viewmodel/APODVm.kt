package com.example.cosmos.apod.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.apod.model.APOD
import com.example.cosmos.apod.repository.APODRepo
import com.example.cosmos.workshared.extensions.getErrorMessage
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class APODVm(
    var apodRepo: APODRepo,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = APODVm::class.qualifiedName
    private var jobGetCredit: Job? = null

    private val mApod = MutableLiveData<APOD>()
    val apod: LiveData<APOD>
        get() = mApod

    init {
        fetchAPODToday()
    }

    override fun onCleared() {
        super.onCleared()
        jobGetCredit?.cancel()
        jobGetCredit = null
    }

    private fun fetchAPODToday(): Job {
        return viewModelScope.launch {
            try {
                mApod.value = apodRepo.getAPODToday()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    fun fetchAPODByDate(date: String): Job {
        return viewModelScope.launch {
            try {
                mApod.value = apodRepo.getAPODByDate(date)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}