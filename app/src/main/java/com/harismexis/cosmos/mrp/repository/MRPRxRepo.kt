package com.harismexis.cosmos.mrp.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.cosmos.BuildConfig
import com.harismexis.cosmos.mrp.api.MRPApi
import com.harismexis.cosmos.mrp.model.response.LatestMRP
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MRPRxRepo {
    private val api: MRPApi

    init {
        api = createMRPApi()
    }

    private fun createMRPApi(): MRPApi {
        return buildRetrofit().create(MRPApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MRP_BASE_URL)
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

    fun getCuriosityLatestMRPSingle(): Single<LatestMRP?> {
        return api.queryCuriosityByLatestMRPSingle(
            BuildConfig.NASA_API_KEY
        )
    }

}