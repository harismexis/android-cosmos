package com.harismexis.cosmos.apod.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.cosmos.BuildConfig
import com.harismexis.cosmos.apod.api.APODApi
import com.harismexis.cosmos.apod.model.APOD
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class APODRepo {

    protected val api: APODApi

    init {
        api = createApi()
    }

    private fun createApi(): APODApi {
        return buildRetrofit().create(APODApi::class.java)
    }

    open fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.APOD_BASE_URL)
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

    suspend fun getAPODToday(): APOD? {
        return getAPOD(null, false)
    }

    suspend fun getAPODByDate(
        dateOfApod: String?
    ): APOD? {
        return api.getAPOD(dateOfApod, false, BuildConfig.NASA_API_KEY)
    }

    private suspend fun getAPOD(
        dateOfApod: String?,
        isHD: Boolean?
    ): APOD? {
        return api.getAPOD(dateOfApod, isHD, BuildConfig.NASA_API_KEY)
    }
}