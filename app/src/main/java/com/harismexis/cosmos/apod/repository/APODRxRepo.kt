package com.harismexis.cosmos.apod.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.cosmos.BuildConfig
import com.harismexis.cosmos.apod.api.APODApi
import com.harismexis.cosmos.apod.model.APOD
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APODRxRepo {
    private val api: APODApi

    init {
        api = createAPODApi()
    }

    private fun createAPODApi(): APODApi {
        return buildRetrofit().create(APODApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.APOD_BASE_URL)
            .client(provideOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(provideGSON()))
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun provideGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

    fun getAPODSingle(
        dateOfApod: String?
    ): Single<APOD?> {
        return getAPODSingle(dateOfApod, true)
    }

    private fun getAPODSingle(
        dateOfApod: String?,
        isHD: Boolean?
    ): Single<APOD?> {
        return api.getAPODSingle(dateOfApod, isHD, BuildConfig.NASA_API_KEY)
    }

}