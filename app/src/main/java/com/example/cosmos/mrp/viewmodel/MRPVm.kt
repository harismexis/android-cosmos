package com.example.cosmos.mrp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.mrp.extensions.toUiModel
import com.example.cosmos.mrp.model.ui.MRPUiModel
import com.example.cosmos.mrp.repository.MRPRepo
import com.example.cosmos.workshared.extensions.getErrorMessage
import com.example.cosmos.workshared.util.network.ConnectivityMonitor
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MRPVm(
    var marsRepo: MRPRepo,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private val TAG = MRPVm::class.qualifiedName
    private var jobGetCredit: Job? = null

    private val mMRP = MutableLiveData<MRPUiModel>()
    val marsPhotosUiModel: LiveData<MRPUiModel>
        get() = mMRP

    override fun onCleared() {
        super.onCleared()
        jobGetCredit?.cancel()
        jobGetCredit = null
    }

    fun getCuriosityLatestPhotos(): Job {
        return viewModelScope.launch {
            try {
                val response = marsRepo.getCuriosityLatestMRP()
                mMRP.value = response.toUiModel()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}