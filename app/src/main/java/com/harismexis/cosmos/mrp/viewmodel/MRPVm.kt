package com.harismexis.cosmos.mrp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.cosmos.mrp.extensions.toUiModels
import com.harismexis.cosmos.mrp.model.ui.MRPUiModel
import com.harismexis.cosmos.mrp.repository.MRPRepo
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class MRPVm @Inject constructor (
    var marsRepo: MRPRepo,
) : ViewModel() {

    private val TAG = MRPVm::class.qualifiedName

    private val mModels = MutableLiveData<List<MRPUiModel>>()
    val models: LiveData<List<MRPUiModel>>
        get() = mModels

    fun fetchCuriosityLatestMRP() {
        viewModelScope.launch {
            try {
                val response = marsRepo.getCuriosityLatestMRP()
                mModels.value = response.toUiModels()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}