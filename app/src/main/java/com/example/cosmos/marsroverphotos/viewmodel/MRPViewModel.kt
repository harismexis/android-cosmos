package com.example.cosmos.marsroverphotos.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.marsroverphotos.extensions.toUiModel
import com.example.cosmos.marsroverphotos.model.ui.MRPUiModel
import com.example.cosmos.marsroverphotos.repository.MRPRepository
import com.example.cosmos.wshared.extensions.getErrorMessage
import com.example.cosmos.wshared.network.ConnectivityMonitor
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MRPViewModel(
    var marsRepo: MRPRepository,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private val TAG = MRPViewModel::class.qualifiedName
    private var jobGetCredit: Job? = null

    private val mMRP = MutableLiveData<MRPUiModel>()
    val marsPhotosUiModel: LiveData<MRPUiModel>
        get() = mMRP

    init {
        //getCuriosityLatestPhotos()
    }

    override fun onCleared() {
        super.onCleared()
        jobGetCredit?.cancel()
        jobGetCredit = null
    }

    fun getCuriosityLatestPhotos(): Job {
        return viewModelScope.launch {
            try {
                val response = marsRepo.getCuriosityLatestPhotos()
                mMRP.value = response.toUiModel()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}