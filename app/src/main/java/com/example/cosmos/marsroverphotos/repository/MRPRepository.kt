package com.example.cosmos.marsroverphotos.repository

import com.example.cosmos.marsroverphotos.api.MRPApi
import com.example.cosmos.marsroverphotos.model.response.LatestMRPResponse
import com.example.cosmos.wshared.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MRPRepository {

    companion object {
        const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/"
    }

    private val api: MRPApi

    init {
        api = createApi()
    }

    private fun createApi(): MRPApi {
        return buildRetrofit().create(MRPApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGSON()))
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    private fun buildGSON(): Gson {
        return GsonBuilder()
            //.setLenient()
            .create()
    }

    suspend fun getCuriosityLatestPhotos(): LatestMRPResponse? {
        return api.queryCuriosityByLatestPhotos(
            Constants.NASA_API_KEY
        )
    }

}