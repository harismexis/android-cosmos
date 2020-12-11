package com.example.cosmos.apod.viewmodel

import android.util.Log
import com.example.cosmos.apod.model.APOD
import com.example.cosmos.apod.repository.APODRxRepo
import com.example.cosmos.workshared.extensions.getErrorMessage
import com.example.cosmos.workshared.util.Action1
import com.example.cosmos.workshared.util.rx.SchedulerProvider
import com.example.cosmos.workshared.util.rx.setSchedulersSingle
import io.reactivex.disposables.CompositeDisposable

class APODEntryVm(
    var apodRepo: APODRxRepo
) {
    private val TAG = APODEntryVm::class.qualifiedName
    private val disposables = CompositeDisposable()

    fun getAPOD(
        onSuccess: Action1<APOD>,
        onError: Action1<Throwable>
    ) {
        disposables.add(apodRepo.getAPODSingle()
            .compose(setSchedulersSingle(SchedulerProvider()))
            .doOnSuccess {
                onSuccess.call(it)
            }
            .doOnError {
                Log.d(TAG, it.getErrorMessage())
                onError.call(it)
            }
            .subscribe())
    }

    fun unbind() {
        disposables.dispose()
    }


}