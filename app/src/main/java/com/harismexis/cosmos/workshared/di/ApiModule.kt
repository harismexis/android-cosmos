package com.harismexis.cosmos.workshared.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class ApiModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

}