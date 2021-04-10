package com.harismexis.cosmos.mrp.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.cosmos.BuildConfig
import com.harismexis.cosmos.mrp.api.MRPApi
import com.harismexis.cosmos.mrp.model.response.LatestMRP
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

open class MRPRepo @Inject constructor() {

    protected val api: MRPApi

    init {
        api = createApi()
    }

    private fun createApi(): MRPApi {
        return buildRetrofit().create(MRPApi::class.java)
    }

    open fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MRP_BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(buildGSON()))
            .build()
    }

    protected fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    protected fun buildGSON(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    suspend fun getCuriosityLatestMRP(): LatestMRP? {
        return api.queryCuriosityByLatestMRP(
            BuildConfig.NASA_API_KEY
        )
    }

}