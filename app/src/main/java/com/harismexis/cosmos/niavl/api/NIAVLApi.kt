package com.harismexis.cosmos.niavl.api

import com.harismexis.cosmos.niavl.model.remote.NIAVLResponse
import retrofit2.http.GET
import retrofit2.http.Query

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
}