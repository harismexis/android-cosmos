package com.example.cosmos.apod.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.apod.model.APODResponse
import com.example.cosmos.apod.repository.APODRepository
import com.example.cosmos.network.ConnectivityMonitor
import com.example.scoredonut.extensions.getErrorMessage
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class APODViewModel (
    var apodRepo: APODRepository,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    companion object {
        const val NASA_API_KEY = "Yncc63cEXKWLYQbhloAc3cIl9fdqzO2rOrdsVYbN"
    }

    private var disposables: CompositeDisposable = CompositeDisposable()
    private val TAG = APODViewModel::class.qualifiedName
    private var jobGetCredit: Job? = null

    private val mApod = MutableLiveData<APODResponse>()
    val apod: LiveData<APODResponse>
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
                mApod.value = apodRepo.getAPOD(NASA_API_KEY, null, true)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}

//    private fun getConnectivityUpdates(): Disposable {
//        return connectivity.getConnectivityUpdates()
//            .filter { it == ConnectivityState.CONNECTED }
//            .doOnNext {
//                jobGetCredit = retrieveCreditScore()
//            }
//            .doOnError {
//                Log.d(TAG, it.getErrorMessage())
//            }
//            .subscribe()
//    }