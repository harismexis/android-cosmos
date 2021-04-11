package com.harismexis.cosmos.niavl.repository

import com.google.gson.Gson
import com.harismexis.cosmos.niavl.api.MediaCollectionApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MediaCollectionRepo @Inject constructor(
    private var okHttpClient: OkHttpClient,
    private var gson: Gson
) {
    private val api: MediaCollectionApi

    init {
        api = createApi()
    }

    private fun createApi(): MediaCollectionApi {
        return buildRetrofit().create(MediaCollectionApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    suspend fun getMediaCollection(url: String): List<String> {
        return api.getMediaCollection(url)
    }

}