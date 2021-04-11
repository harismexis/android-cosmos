package com.harismexis.cosmos.niavl.api

import com.harismexis.cosmos.niavl.model.remote.NIAVLResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * NASA Image and Video Library API
 * https://api.nasa.gov/
 * https://images.nasa.gov/
 * https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf
 */
interface NIAVLApi {

    @GET("search")
    suspend fun search(
        @Query("q") query: String
    ): NIAVLResponse?

    @GET
    suspend fun getMediaCollection(
        @Url url: String
    ): List<String>

}