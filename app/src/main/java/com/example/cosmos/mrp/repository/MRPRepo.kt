package com.example.cosmos.mrp.repository

import com.example.cosmos.BuildConfig
import com.example.cosmos.mrp.api.MRPApi
import com.example.cosmos.mrp.model.response.LatestMRPResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MRPRepo {

    private val api: MRPApi

    init {
        api = createApi()
    }

    private fun createApi(): MRPApi {
        return buildRetrofit().create(MRPApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MRP_BASE_URL)
            .client(createOkHttpClient())
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

    suspend fun getCuriosityLatestPhotos(): LatestMRPResponse? {
        return api.queryCuriosityByLatestPhotos(
            BuildConfig.NASA_API_KEY
        )
    }

}