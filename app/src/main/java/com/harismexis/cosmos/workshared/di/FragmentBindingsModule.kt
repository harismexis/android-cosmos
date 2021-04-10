package com.harismexis.cosmos.workshared.di

import com.harismexis.cosmos.apod.activity.APODFragment
import com.harismexis.cosmos.home.activity.HomeFragment
import com.harismexis.cosmos.mrp.activity.MRPFragment
import com.harismexis.cosmos.workshared.activity.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun apodFragment(): APODFragment

    @ContributesAndroidInjector
    abstract fun mrpFragment(): MRPFragment

}
