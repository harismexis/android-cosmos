package com.example.cosmos.apod.viewmodel

import com.example.cosmos.BuildConfig
import com.example.cosmos.apod.model.APODResponse
import com.example.cosmos.apod.repository.APODRxRepo
import com.example.cosmos.workshared.util.Action1
import com.example.cosmos.workshared.util.SchedulerProvider
import com.example.cosmos.workshared.util.setSchedulersSingle
import io.reactivex.disposables.CompositeDisposable

class APODEntryVm(
    var apodRepo: APODRxRepo
) {
    private val disposables: CompositeDisposable? = CompositeDisposable()
    private val TAG = APODEntryVm::class.qualifiedName

    fun unbind() {
        disposables?.dispose()
    }

    fun getAPOD(
        onSuccess: Action1<APODResponse>,
        onError: Action1<Throwable>
    ) {
        disposables?.add(apodRepo.getAPODSingle(BuildConfig.NASA_API_KEY)
            .compose(setSchedulersSingle(SchedulerProvider()))
            .doOnSuccess {
                onSuccess.call(it)
            }
            .doOnError {
                onError.call(it)
            }
            .subscribe())
    }

}