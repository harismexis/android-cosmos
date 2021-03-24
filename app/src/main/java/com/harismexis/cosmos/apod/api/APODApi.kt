package com.harismexis.cosmos.apod.api

import com.harismexis.cosmos.apod.model.APOD
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Astronomy Picture of the Day
 * NASA API
 * https://api.nasa.gov/
 * https://apod.nasa.gov/apod/astropix.html
 */
interface APODApi {

    @GET("apod")
    suspend fun getAPOD(
        @Query("date") dateOfApod: String?,
        @Query("hd") isHD: Boolean?,
        @Query("api_key") apiKey: String
    ): APOD?

    @GET("apod")
    fun getAPODSingle(
        @Query("date") dateOfApod: String?,
        @Query("hd") isHD: Boolean?,
        @Query("api_key") apiKey: String
    ): Single<APOD?>
}