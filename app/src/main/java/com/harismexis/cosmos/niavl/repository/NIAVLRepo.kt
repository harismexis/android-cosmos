package com.harismexis.cosmos.niavl.repository

import com.google.gson.Gson
import com.harismexis.cosmos.BuildConfig
import com.harismexis.cosmos.niavl.api.NIAVLApi
import com.harismexis.cosmos.niavl.model.remote.NIAVLResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NIAVLRepo @Inject constructor(
    private var okHttpClient: OkHttpClient,
    private var gson: Gson
) {
    private val api: NIAVLApi

    init {
        api = createApi()
    }

    private fun createApi(): NIAVLApi {
        return buildRetrofit().create(NIAVLApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NIAVL_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    suspend fun search(query: String): NIAVLResponse? {
        return api.search(query)
    }

}