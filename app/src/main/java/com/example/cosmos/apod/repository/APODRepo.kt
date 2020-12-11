package com.example.cosmos.apod.repository

import com.example.cosmos.BuildConfig
import com.example.cosmos.apod.api.APODApi
import com.example.cosmos.apod.model.APODResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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

    suspend fun getAPOD(
        apiKey: String
    ): APODResponse? {
        return getAPOD(null, true, apiKey)
    }

    suspend fun getAPOD(
        dateOfApod: String?,
        isHD: Boolean?,
        apiKey: String
    ): APODResponse? {
        return api.getAPOD(dateOfApod, isHD, apiKey)
    }
}