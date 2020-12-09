package com.example.cosmos.apod.api

import com.example.cosmos.apod.model.APODResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Astronomy Picture of the Day API
 * https://api.nasa.gov/
 * https://apod.nasa.gov/apod/astropix.html
 */
interface APODApi {

    @GET("apod")
    suspend fun getAPOD(
        @Query("date") dateOfApod: String?,
        @Query("hd") isHD: Boolean?,
        @Query("api_key") apiKey: String
    ): APODResponse?
}