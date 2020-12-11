package com.example.cosmos.mrp.viewmodel

import android.util.Log
import com.example.cosmos.apod.viewmodel.APODEntryVm
import com.example.cosmos.mrp.model.response.MRPItem
import com.example.cosmos.mrp.repository.MRPRxRepo
import com.example.cosmos.workshared.extensions.getErrorMessage
import com.example.cosmos.workshared.util.Action1
import com.example.cosmos.workshared.util.rx.SchedulerProvider
import com.example.cosmos.workshared.util.rx.setSchedulersSingle
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