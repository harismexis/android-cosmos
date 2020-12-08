package com.example.cosmos.apod.api

import com.example.cosmos.apod.model.APODResponse
import retrofit2.http.*

interface APODApi {

    @GET("apod")
    suspend fun getAPOD(
        @Query("api_key") apiKey: String,
        @Query("date") dateOfApod: String?,
        @Query("hd") isHD: Boolean?
    ): APODResponse?
}