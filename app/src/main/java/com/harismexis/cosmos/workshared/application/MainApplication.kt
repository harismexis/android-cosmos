package com.harismexis.cosmos.workshared.application

import com.harismexis.cosmos.workshared.di.DaggerMainComponent
import com.harismexis.cosmos.workshared.di.MainComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class MainApplication : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    private lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        initRxErrorHandler()
    }

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { ex: Throwable ->

        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerMainComponent.factory().create(this)
        component.inject(this)
        return component
    }

}