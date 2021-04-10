package com.harismexis.cosmos.mrp.repository

import com.google.gson.Gson
import com.harismexis.cosmos.BuildConfig
import com.harismexis.cosmos.mrp.api.MRPApi
import com.harismexis.cosmos.mrp.model.response.LatestMRP
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MRPRepo @Inject constructor(
    private var okHttpClient: OkHttpClient,
    private var gson: Gson
) {
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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    suspend fun getCuriosityLatestMRP(): LatestMRP? {
        return api.queryCuriosityByLatestMRP(
            BuildConfig.NASA_API_KEY
        )
    }

}