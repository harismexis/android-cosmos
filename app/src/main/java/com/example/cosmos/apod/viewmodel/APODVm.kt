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

class APODVm (
    var apodRepo: APODRepo,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = APODVm::class.qualifiedName
    private var jobGetCredit: Job? = null

    private val mApod = MutableLiveData<APOD>()
    val apod: LiveData<APOD>
        get() = mApod

    init {
        retrieveAPOD()
    }

    override fun onCleared() {
        super.onCleared()
        jobGetCredit?.cancel()
        jobGetCredit = null
    }

    fun bind() {

    }

    fun unbind() {

    }

    private fun retrieveAPOD(): Job {
        return viewModelScope.launch {
            try {
                mApod.value = apodRepo.getAPOD()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}