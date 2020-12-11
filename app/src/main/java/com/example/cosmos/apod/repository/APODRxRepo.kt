package com.example.cosmos.apod.repository

import com.example.cosmos.BuildConfig
import com.example.cosmos.apod.model.APODResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APODRxRepo : APODRepo() {

    override fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.APOD_BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGSON()))
            .build()
    }

    fun getAPODSingle(
        apiKey: String
    ): Single<APODResponse?> {
        return api.getAPODSingle(null, true, apiKey)
    }

    fun getAPODSingle(
        dateOfApod: String?,
        isHD: Boolean?,
        apiKey: String
    ): Single<APODResponse?> {
        return api.getAPODSingle(dateOfApod, isHD, apiKey)
    }

}