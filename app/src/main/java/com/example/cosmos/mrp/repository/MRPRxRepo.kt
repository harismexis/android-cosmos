package com.example.cosmos.mrp.repository

import com.example.cosmos.BuildConfig
import com.example.cosmos.mrp.model.response.LatestMRP
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MRPRxRepo : MRPRepo() {

    override fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MRP_BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGSON()))
            .build()
    }

    fun getCuriosityLatestMRPSingle(): Single<LatestMRP?> {
        return api.queryCuriosityByLatestMRPSingle(
            BuildConfig.NASA_API_KEY
        )
    }

}