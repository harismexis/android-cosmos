package com.harismexis.cosmos.niavl.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.cosmos.niavl.extensions.toUiModels
import com.harismexis.cosmos.niavl.model.NIAVLCollectionItem
import com.harismexis.cosmos.niavl.repository.NIAVLRepo
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class NIAVLVm @Inject constructor (
    var niavlRepo: NIAVLRepo,
) : ViewModel() {

    private val TAG = NIAVLVm::class.qualifiedName

    private val mModels = MutableLiveData<List<NIAVLCollectionItem>>()
    val models: LiveData<List<NIAVLCollectionItem>>
        get() = mModels

    fun fetchCuriosityLatestMRP() {
        viewModelScope.launch {
            try {
                val response = niavlRepo.search("orion")
                mModels.value = response?.toUiModels()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}