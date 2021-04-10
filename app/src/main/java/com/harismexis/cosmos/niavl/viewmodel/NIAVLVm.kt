package com.harismexis.cosmos.niavl.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.cosmos.niavl.extensions.toUiModels
import com.harismexis.cosmos.niavl.model.ui.NIAVLUiModel
import com.harismexis.cosmos.niavl.repository.NIAVLRepo
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class NIAVLVm @Inject constructor(
    var niavlRepo: NIAVLRepo,
) : ViewModel() {

    private val TAG = NIAVLVm::class.qualifiedName

    private val mModels = MutableLiveData<List<NIAVLUiModel>>()
    val models: LiveData<List<NIAVLUiModel>>
        get() = mModels

    fun searchNIAVL(query: String) {
        if (query.isNotBlank()) search(query)
    }

    private fun search(query: String) {
        viewModelScope.launch {
            try {
                val response = niavlRepo.search(query)
                mModels.value = response?.toUiModels()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}