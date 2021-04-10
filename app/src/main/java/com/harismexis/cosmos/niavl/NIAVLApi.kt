package com.harismexis.cosmos.niavl

import com.harismexis.cosmos.apod.model.APOD
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Astronomy Picture of the Day
 * NASA Image and Video Library API
 * https://api.nasa.gov/
 * https://images.nasa.gov/
 * https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf
 */
interface NIAVLApi {

    @GET("search")
    suspend fun getAPOD(
        @Query("q") query: String
    ): APOD?
}