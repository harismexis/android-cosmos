package com.example.cosmos.mrp.viewmodel

import com.example.cosmos.apod.viewmodel.APODEntryVm
import com.example.cosmos.mrp.repository.MRPRepo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job

class MRPEntryVm(
    var mrpRepo: MRPRepo
) {

    companion object {}

    private val disposables: CompositeDisposable? = CompositeDisposable()
    private val TAG = APODEntryVm::class.qualifiedName
    private var jobGetCredit: Job? = null

    init {

    }

    fun unbind() {
        disposables?.dispose()
    }

//    fun getMRP(
//        onSuccess: Action1<APODResponse>,
//        onError: Action1<Throwable>
//    ) {
//        disposables?.add(mrpRepo.getAPODSingle(BuildConfig.NASA_API_KEY)
//            .compose(setSchedulersSingle(SchedulerProvider()))
//            .doOnSuccess {
//                onSuccess.call(it)
//            }
//            .doOnError {
//                onError.call(it)
//            }
//            .subscribe())
//    }

}