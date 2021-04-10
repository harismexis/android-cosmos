package com.harismexis.cosmos.workshared.di

import com.harismexis.cosmos.apod.activity.APODActivity
import com.harismexis.cosmos.home.activity.HomeActivity
import com.harismexis.cosmos.mrp.activity.MRPActivity
import com.harismexis.cosmos.workshared.activity.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun apodActivity(): APODActivity

    @ContributesAndroidInjector
    abstract fun mrpActivity(): MRPActivity

}
