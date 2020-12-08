package com.example.cosmos.apod.repository

import com.example.cosmos.apod.api.APODApi
import com.example.cosmos.apod.model.APODResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APODRepository {

    companion object {
        const val BASE_URL = "https://api.nasa.gov/planetary/"
    }

    private val api: APODApi

    init {
        api = createApi()
    }

    private fun createApi(): APODApi {
        return buildRetrofit().create(APODApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGSON()))
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    private fun buildGSON(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    suspend fun getAPOD(
        apiKey: String,
        dateOfApod: String?,
        isHD: Boolean?
    ): APODResponse? {
        return api.getAPOD(apiKey, dateOfApod, isHD)
    }

}