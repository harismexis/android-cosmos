package com.harismexis.cosmos.mrp.viewmodel

import android.util.Log
import com.harismexis.cosmos.apod.viewmodel.APODEntryVm
import com.harismexis.cosmos.mrp.model.response.MRPItem
import com.harismexis.cosmos.mrp.repository.MRPRxRepo
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import com.harismexis.cosmos.workshared.util.functional.Action1
import com.harismexis.cosmos.workshared.util.rx.SchedulerProvider
import com.harismexis.cosmos.workshared.util.rx.setSchedulersSingle
import io.reactivex.disposables.CompositeDisposable

class MRPEntryVm(
    var mrpRepo: MRPRxRepo
) {
    private val TAG = APODEntryVm::class.qualifiedName
    private val disposables = CompositeDisposable()

    fun getCuriosityLatestMRP(
        onSuccess: Action1<MRPItem>,
        onError: Action1<Throwable>
    ) {
        disposables.add(mrpRepo.getCuriosityLatestMRPSingle()
            .compose(setSchedulersSingle(SchedulerProvider()))
            .doOnSuccess {
                onSuccess.call(it?.latest_photos?.get(0))
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