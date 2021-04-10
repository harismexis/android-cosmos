package com.harismexis.cosmos.apod.repository

import com.google.gson.Gson
import com.harismexis.cosmos.BuildConfig
import com.harismexis.cosmos.apod.api.APODApi
import com.harismexis.cosmos.apod.model.APOD
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class APODRepo @Inject constructor(
    private var okHttpClient: OkHttpClient,
    private var gson: Gson
) {
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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    suspend fun getAPODToday(): APOD? {
        return getAPOD(null, false)
    }

    suspend fun getAPODByDate(
        dateOfApod: String?
    ): APOD? {
        return getAPOD(dateOfApod, false)
    }

    private suspend fun getAPOD(
        dateOfApod: String?,
        isHD: Boolean?
    ): APOD? {
        return api.getAPOD(dateOfApod, isHD, BuildConfig.NASA_API_KEY)
    }
}