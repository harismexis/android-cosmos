package com.harismexis.cosmos.mediaplayer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.cosmos.niavl.repository.MediaCollectionRepo
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class MediaPlayerVm @Inject constructor(
    var repo: MediaCollectionRepo,
) : ViewModel() {

    private val TAG = MediaPlayerVm::class.qualifiedName

    private val mMediaUrls = MutableLiveData<List<String>>()
    val mediaUrls: LiveData<List<String>>
        get() = mMediaUrls

    fun fetchMediaUrls(mediaCollectionUrl: String) {
        viewModelScope.launch {
            try {
                val response = repo.getMediaCollection(mediaCollectionUrl)
                mMediaUrls.value = response
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}