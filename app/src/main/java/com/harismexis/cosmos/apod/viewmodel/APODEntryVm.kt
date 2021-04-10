package com.harismexis.cosmos.apod.viewmodel

import android.util.Log
import com.harismexis.cosmos.apod.model.APOD
import com.harismexis.cosmos.apod.repository.APODRxRepo
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import com.harismexis.cosmos.workshared.util.functional.Action1
import com.harismexis.cosmos.workshared.util.rx.SchedulerProvider
import com.harismexis.cosmos.workshared.util.rx.setSchedulersSingle
import io.reactivex.disposables.CompositeDisposable

class APODEntryVm (
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