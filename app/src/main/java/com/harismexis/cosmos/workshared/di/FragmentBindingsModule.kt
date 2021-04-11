package com.harismexis.cosmos.workshared.di

import com.harismexis.cosmos.apod.fragment.APODFragment
import com.harismexis.cosmos.apod.fragment.APODPlayerFragment
import com.harismexis.cosmos.home.fragment.HomeFragment
import com.harismexis.cosmos.mediaplayer.MediaPlayerFragment
import com.harismexis.cosmos.mrp.fragment.MRPFragment
import com.harismexis.cosmos.niavl.fragment.NIAVLFragment
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

    @ContributesAndroidInjector
    abstract fun apodPlayerFragment(): APODPlayerFragment

    @ContributesAndroidInjector
    abstract fun nIAVLFragment(): NIAVLFragment

    @ContributesAndroidInjector
    abstract fun mediaPlayerFragment(): MediaPlayerFragment

}
