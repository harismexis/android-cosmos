package com.example.cosmos.apod.viewmodel

import android.util.Log
import com.example.cosmos.apod.model.APOD
import com.example.cosmos.apod.repository.APODRxRepo
import com.example.cosmos.workshared.extensions.getErrorMessage
import com.example.cosmos.workshared.util.functional.Action1
import com.example.cosmos.workshared.util.rx.SchedulerProvider
import com.example.cosmos.workshared.util.rx.setSchedulersSingle
import io.reactivex.disposables.CompositeDisposable

class APODEntryVm(
    var apodRepo: APODRxRepo
) {

    companion object {
        private const val HOME_DATE = "2020-12-18"
    }

    private val tag = APODEntryVm::class.qualifiedName
    private val disposables = CompositeDisposable()

    fun getHomeAPOD(
        onSuccess: Action1<APOD>,
        onError: Action1<Throwable>
    ) {
        disposables.add(apodRepo.getAPODSingle(HOME_DATE)
            .compose(setSchedulersSingle(SchedulerProvider()))
            .doOnSuccess {
                onSuccess.call(it)
            }
            .doOnError {
                Log.d(tag, it.getErrorMessage())
                onError.call(it)
            }
            .subscribe())
    }

    fun unbind() {
        disposables.dispose()
    }


}